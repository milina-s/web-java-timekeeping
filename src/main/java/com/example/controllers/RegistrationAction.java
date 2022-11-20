package com.example.controllers;

public enum RegistrationAction {
    LOGIN,
    REGISTER,
    GO_BACK;

    public static RegistrationAction getAction(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "LOGIN" -> LOGIN;
            case "REGISTER" -> REGISTER;
            default -> GO_BACK;
        };
    }
}
