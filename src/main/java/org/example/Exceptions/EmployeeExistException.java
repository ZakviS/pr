package org.example.Exceptions;

public class EmployeeExistException extends RuntimeException{

    public EmployeeExistException( String reason) {
        super(reason);
    }
}
