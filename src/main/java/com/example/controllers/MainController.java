package com.example.controllers;

import com.example.delegator.ServiceDelegator;
import com.example.session.CurrentSession;
import com.example.view.RegistrationView;


public class MainController {

    private final ServiceDelegator serviceDelegator;

    public MainController(ServiceDelegator serviceDelegator) {
        this.serviceDelegator = serviceDelegator;
    }

    public void start () throws ClassNotFoundException {
        authorize();
        switch (CurrentSession.getRole()){
            case USER -> startUser();
            case ADMIN -> startAdmin();
        }
    }

    public void startUser() {
        UserController userController = new UserController(serviceDelegator);
        userController.start();
    }

    public void startAdmin() {
        AdminController adminController = new AdminController(serviceDelegator);
        adminController.start();
    }

    public void authorize() throws ClassNotFoundException {
        RegistrationController registrationController = new RegistrationController(serviceDelegator, new RegistrationView());
        registrationController.start();
    }

    private void deleteCategory () {

    }
}
