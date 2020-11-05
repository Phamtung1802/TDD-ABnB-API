package com.TDD.ABnB.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter

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
