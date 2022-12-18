package com.example.controllers;

import com.example.delegator.ServiceDelegator;

public class UserController {

    private final ServiceDelegator serviceDelegator;

    public UserController(ServiceDelegator serviceDelegator) {
        this.serviceDelegator = serviceDelegator;
    }

    public void start () {

    }
}
