package com.jtsp.springaopdemo.aop;

import com.jtsp.springaopdemo.exception.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                          WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Method argument not valid exception : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(HttpRequestMethodNotSupportedException ex,
                                                                          WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        errors.put("method", ex.getMethod());
        errors.put("exception", ex.getMessage());
        log.error("HTTP request method not supported exception : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex,
                                                                WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        errors.put("violdations", ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList()
                .toString()
        );
        errors.put("exception", ex.getMessage());
        log.error("Constraint Violation exception : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(UserNotFoundException ex,
                                                                                  WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        errors.put("exception", ex.getMessage());
        log.error("User Not Found exception : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(HandlerMethodValidationException ex,
                                                                                  WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        errors.put("violations", ex.getAllValidationResults()
                .stream()
                .map(r -> r.getArgument() + " " + r.getResolvableErrors())
                .toList()
                .toString()
        );
        errors.put("exception", ex.getMessage());
        log.error("handler method violation exception : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(RuntimeException ex,
                                                                                  WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        errors.put("path", webRequest.getContextPath());
        errors.put("web request desc", webRequest.getDescription(false));
        errors.put("exception", ex.getMessage());
        log.error("internal server error : {}", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
