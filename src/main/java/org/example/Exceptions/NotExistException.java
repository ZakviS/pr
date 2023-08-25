package org.example.Exceptions;

public class NotExistException extends RuntimeException{

    public NotExistException(String reason) {
        super(reason);
    }

}
