package com.example.entities;

public enum UserRole {
    USER,
    ADMIN;

    public static UserRole getUserRole(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "USER" -> USER;
            case "ADMIN" -> ADMIN;
            default -> null;
        };
    }
}
