package com.example.controllers;

public enum RegistrationAction {
    LOGIN,
    REGISTER;

    public static RegistrationAction getAction(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "LOGIN" -> LOGIN;
            case "REGISTER" -> REGISTER;
            default -> null;
        };
    }
}
