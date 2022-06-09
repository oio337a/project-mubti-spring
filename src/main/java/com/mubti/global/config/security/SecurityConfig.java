package com.mubti.global.config.security;

import com.mubti.domain.user.repository.UserRefreshTokenRepository;
import com.mubti.global.config.properties.AppProperties;
import com.mubti.global.config.properties.CorsProperties;
import com.mubti.global.common.oauth.entity.RoleType;
import com.mubti.global.common.oauth.exception.RestAuthenticationEntryPoint;
import com.mubti.global.common.oauth.filter.TokenAuthenticationFilter;
import com.mubti.global.common.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.mubti.global.common.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.mubti.global.common.oauth.handler.TokenAccessDeniedHandler;
import com.mubti.global.common.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.mubti.global.common.oauth.service.CustomOAuth2UserService;
import com.mubti.global.common.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // cors 설정.
                .and()
                .sessionManagement() // 세션 관리 매니저 생성
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않는다. (JWT 방식을 사용하기 위한 설정)
                .and()
                .csrf().disable() // rest api를 이용한 서버는 session 기반 인증과는 다르게 stateless하기 때문에 서버에 인증 정보를 보관하지 않으므로 불필요한 csrf 코드 제거
                .formLogin().disable() // 토큰 방식으로 인증처리를 하므로 form login을 사용하지 않는다.
                .httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .exceptionHandling() // 예외 처리 기능 작동
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 인증 실패 처리를 설정한다.
                .accessDeniedHandler(tokenAccessDeniedHandler) // 접근 거부 때 처리 할 핸들러를 설정한다.
                .and()
                .authorizeRequests() // requestMatchers를 구현하여 요청의 접근을 제한할 수 있다.
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // Preflight 요청은 모두 허용한다.
                .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode()) // USER 권한을 가지는 사용자만 접근 가능
                .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode()) // ADMIN 권한을 가지는 사용자만 접근 가능
                .anyRequest().authenticated() // 나머지 요청은 전부 인증 필요
                .and()
                .oauth2Login() // oauth2.0 설정
                .authorizationEndpoint() // 인가 엔드포인트 설정
                .baseUri("/oauth2/authorization") // 인가 엔드포인트 설정
                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()) // 인가 요청 정보 저장소 설정
                .and()
                .redirectionEndpoint() // 인가 응답 엔드포인트 설정
                .baseUri("/*/oauth2/code/*") // 인가 응답 엔드포인트 설정
                .and()
                .userInfoEndpoint() // 유저 정보 엔드포인트 설정
                .userService(oAuth2UserService) // 유저 정보 서비스 설정
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler())
                .failureHandler(oAuth2AuthenticationFailureHandler()); // 인증 성공, 실패 핸들러 설정

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 커스텀 필터 등록. UsernamePasswordAuthenticationFilter 보다 더 먼저 실행
    }

    /*
    * auth 매니저 설정
    * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
    * 토큰 필터 설정
    * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
    * 쿠키 기반 인가 Repository
    * 인가 응답을 연계 하고 검증할 때 사용.
    * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
    * Oauth 인증 성공 핸들러
    * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
    * Cors 설정
    * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}
