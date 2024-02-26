package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.exceptions.AlreadyExistException;
import com.ozgurokanozdal.habitTracker.exceptions.CustomResponseBody;
import com.ozgurokanozdal.habitTracker.exceptions.ValidationErrorBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class AuthControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorBody> handleValidationError(MethodArgumentNotValidException e, HttpServletRequest request){
        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error ->{
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field,message);
        });
        ValidationErrorBody validationErrorBody = new ValidationErrorBody(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                errors,
                LocalDateTime.now());
        return new ResponseEntity<>(validationErrorBody,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<CustomResponseBody> handleAlreadyExistError(AlreadyExistException e,HttpServletRequest request){

        CustomResponseBody customResponseBody = new CustomResponseBody(
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(customResponseBody,HttpStatus.CONFLICT);
    }
}
