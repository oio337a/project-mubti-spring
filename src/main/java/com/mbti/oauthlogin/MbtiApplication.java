package com.mbti.oauthlogin;

import com.mbti.oauthlogin.config.properties.AppProperties;
import com.mbti.oauthlogin.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class MbtiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbtiApplication.class, args);
    }

}
