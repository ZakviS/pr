package org.example.Exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException( String reason) {
        super(reason);
    }

}
