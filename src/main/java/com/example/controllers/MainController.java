package com.example.controllers;

import com.example.delegator.ServiceDelegator;
import com.example.session.CurrentSession;
import com.example.view.MainView;


public class MainController {

    private final ServiceDelegator serviceDelegator;
    private final MainView mainView;

    public MainController(ServiceDelegator serviceDelegator) {
        this.serviceDelegator = serviceDelegator;
        mainView = new MainView();
    }

    public void start () throws ClassNotFoundException {
        do {
            authorize();
            switch (CurrentSession.getRole()) {
                case USER -> startUser();
                case ADMIN -> startAdmin();
            }
        } while (!mainView.ifLeave());
    }

    public void startUser() throws ClassNotFoundException {
        UserController userController = new UserController(serviceDelegator);
        userController.start();
    }

    public void startAdmin() {
        AdminController adminController = new AdminController(serviceDelegator);
        adminController.start();
    }

    public void authorize() throws ClassNotFoundException {
        RegistrationController registrationController = new RegistrationController(serviceDelegator);
        registrationController.start();
    }

    private void deleteCategory () {

    }
}
