package com.TDD.ABnB.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError {
    private String exception;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(String exception,HttpStatus status, String message, List<String> errors) {
        super();
        this.exception=exception;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(String exception,HttpStatus status, String message, String error) {
        super();
        this.exception=exception;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

}
