package com.TDD.ABnB.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class DuplilcateUserException extends Exception{
    public DuplilcateUserException(String message) {
        super(message);
    }
}
