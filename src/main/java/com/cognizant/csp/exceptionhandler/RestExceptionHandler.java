package com.cognizant.csp.exceptionhandler;

import com.cognizant.csp.enums.ProcessingResult;
import com.cognizant.csp.model.CustomerStatementProcessorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

/**
 * This class is used to handle exception in case of any error.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle any exception response entity.
     *
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException() {
        return buildResponseEntity(new CustomerStatementProcessorResponse(ProcessingResult.INTERNAL_SERVER_ERROR, Collections.emptyList()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new CustomerStatementProcessorResponse(ProcessingResult.BAD_REQUEST, Collections.emptyList()), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(CustomerStatementProcessorResponse errorResponse, HttpStatus httpStatus) {
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
