package com.example.entities;

public enum RequestType {
    REMOVE,
    ADD;

    public static RequestType getRequestType(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "REMOVE" -> REMOVE;
            case "ADD" -> ADD;
            default -> null;
        };
    }
}
