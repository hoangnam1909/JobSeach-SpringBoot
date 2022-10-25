package com.nhn.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException e) {
        Map<String, Object> errors = new HashMap<>();
//        System.out.println("in onConstraintValidationException - start");
        String error = "blank";
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error = violation.getMessage();
//            System.out.println(error);
            errors.put("error", error);
        }
//        System.out.println("in onConstraintValidationException - end");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<Object> onException(Exception e) {
        Map<String, Object> errors = new HashMap<>();
//        System.out.println("in onConstraintValidationException - start");
        String error = e.getMessage();
//            System.out.println(error);
        errors.put("error", error);
//        System.out.println("in onConstraintValidationException - end");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName;
            try {
                fieldName = ((FieldError) error).getField();
            } catch (Exception e) {
                fieldName = error.getObjectName();
            }

            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
