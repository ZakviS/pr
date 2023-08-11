package org.example.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handle(EmployeeNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(EmployeeExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

//    @ExceptionHandler
//    public ResponseEntity<String> handle(BusinessException e){
//        return new ResponseEntity<>( HttpStatus.I_AM_A_TEAPOT);
//    }

    @ExceptionHandler
    public ResponseEntity<String> handle(BusinessException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

}
