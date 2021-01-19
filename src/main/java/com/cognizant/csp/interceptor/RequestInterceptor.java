package com.cognizant.csp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is used to intercept all the requests.
 */
@Slf4j
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info("API endPoint - {} is invoked at {}.", request.getRequestURI(), System.currentTimeMillis());
        return true;

    }

}