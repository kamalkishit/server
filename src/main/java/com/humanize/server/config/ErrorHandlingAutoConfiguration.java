package com.humanize.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.humanize.server.exception.GlobalDefaultExceptionHandlerResolver;

@Configuration
public class ErrorHandlingAutoConfiguration {

    private static final String BASE_NAME = Config.EXCEPTIONS;

    @Bean
    public GlobalDefaultExceptionHandlerResolver globalDefaultExceptionHandlerResolver() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(BASE_NAME);
        return new GlobalDefaultExceptionHandlerResolver(resourceBundleMessageSource);
    }

}
