package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.ValidationErrorBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class AuthControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorBody> handleValidationError(ConstraintViolationException e, HttpServletRequest request){

        ValidationErrorBody validationErrorBody = new ValidationErrorBody(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                // constraint violation returns set of errors...
                e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet()),
                LocalDateTime.now());
        return new ResponseEntity<>(validationErrorBody,HttpStatus.BAD_REQUEST);


    }
}
