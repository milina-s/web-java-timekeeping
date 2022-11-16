package com.example.entities;

public enum UserRole {
    USER,
    ADMIN,
    NOT_AUTHORIZED;

    public static UserRole getUserRole(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "USER" -> USER;
            case "ADMIN" -> ADMIN;
            case "NOT_AUTHORIZED" -> NOT_AUTHORIZED;
            default -> null;
        };
    }
}
