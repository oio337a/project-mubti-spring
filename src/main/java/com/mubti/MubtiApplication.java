package com.mubti;

import com.mubti.global.config.properties.AppProperties;
import com.mubti.global.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class MubtiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MubtiApplication.class, args);
    }

}
