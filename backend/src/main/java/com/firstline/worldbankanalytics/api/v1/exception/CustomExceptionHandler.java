package com.firstline.worldbankanalytics.api.v1.exception;

import com.firstline.worldbankanalytics.exception.NoSuchEconomicPolicyException;
import com.firstline.worldbankanalytics.exception.NoSuchEconomicPolicyPredictionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchEconomicPolicyException.class)
    protected ResponseEntity<Exception> handleNoSuchEconomicPolicyException() {
        return new ResponseEntity<>(new Exception("No such economic policy"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchEconomicPolicyPredictionException.class)
    protected ResponseEntity<Exception> handleNoSuchEconomicPolicyPredictionException() {
        return new ResponseEntity<>(new Exception("No such economic policy prediction"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class Exception {
        private String message;
    }
}
