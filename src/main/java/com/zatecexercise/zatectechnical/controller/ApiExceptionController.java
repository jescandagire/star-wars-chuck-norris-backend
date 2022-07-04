package com.zatecexercise.zatectechnical.controller;

import com.zatecexercise.zatectechnical.config.ApiExceptionResult;
import com.zatecexercise.zatectechnical.exception.SearchKeyLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(SearchKeyLengthException.class)
    public ResponseEntity<ApiExceptionResult> handleSearchKeyLengthException(SearchKeyLengthException ex){
        ApiExceptionResult apiExceptionResult = new ApiExceptionResult(LocalDateTime.now(),ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptionResult);
    }
}
