package com.rabobank.csp.interceptor.config;

import com.rabobank.csp.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * This class is used to register request Interceptor
 */
@EnableConfigurationProperties
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptor requestInterceptor;

    // This method will get invoked by container while deployment
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

        // adding interceptor to registry
        interceptorRegistry.addInterceptor(requestInterceptor);
    }
}