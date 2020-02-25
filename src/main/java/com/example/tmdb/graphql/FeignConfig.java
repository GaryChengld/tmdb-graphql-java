package com.example.tmdb.graphql;

import static com.example.tmdb.graphql.TmdbConstants.PARAM_API_KEY;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

/**
 * @author Gary Cheng
 */
@Configuration
public class FeignConfig {
    @Value("${tmdb.apiKey}")
    private String apiKey;

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return requestTemplate -> {
            requestTemplate.query(PARAM_API_KEY, apiKey);
        };
    }    
}
