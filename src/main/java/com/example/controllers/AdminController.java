package com.example.controllers;

import com.example.delegator.ServiceDelegator;

public class AdminController {
    private final ServiceDelegator serviceDelegator;

    public AdminController(ServiceDelegator serviceDelegator) {
        this.serviceDelegator = serviceDelegator;
    }

    public void start() {

    }
}
