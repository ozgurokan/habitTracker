package com.ozgurokanozdal.habitTracker.controller;


import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.CustomResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class HabitControllerAdvice {

    @ExceptionHandler(ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomResponseBody> handleHabitNotFoundException(ContentNotFoundException e, HttpServletRequest request){
        CustomResponseBody customResponseBody = new CustomResponseBody(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(customResponseBody,HttpStatus.NOT_FOUND);
    }
}
