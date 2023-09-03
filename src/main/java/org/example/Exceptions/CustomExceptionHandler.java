package org.example.Exceptions;

import org.example.AOP.AOPServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AOPServices.class);


    @ExceptionHandler
    public ResponseEntity<Object> handle(NotExistException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(ExistException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler
//    public ResponseEntity<String> handle(BusinessException e){
//        return new ResponseEntity<>( HttpStatus.I_AM_A_TEAPOT);
//    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(BusinessException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception e){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        );

        log.error("Метод был аварийно завершен с исключением - {}",
                exceptionResponse.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
