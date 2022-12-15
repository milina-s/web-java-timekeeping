package com.example.entities;

public enum RequestStatus {
    PENDING,
    CONFIRMED,
    DECLINED;

    public static RequestStatus getRequestStatus(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "PENDING" -> PENDING;
            case "CONFIRMED" -> CONFIRMED;
            case "DECLINED" -> DECLINED;
            default -> null;
        };
    }
}
