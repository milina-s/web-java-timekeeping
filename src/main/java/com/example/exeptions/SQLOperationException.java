package com.example.exeptions;

public class SQLOperationException extends RuntimeException {

    public SQLOperationException(String message) {
        super(message);
        printStackTrace();
    }

}
