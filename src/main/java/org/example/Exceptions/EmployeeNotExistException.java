package org.example.Exceptions;

public class EmployeeNotExistException  extends RuntimeException{

    public EmployeeNotExistException( String reason) {
        super(reason);
    }

}
