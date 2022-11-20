package com.example.controllers;

import com.example.delegator.ServiceDelegator;
import com.example.entities.UserRole;
import com.example.services.RegistrationService;
import com.example.session.CurrentSession;
import com.example.view.RegistrationView;

public class RegistrationController {

    private final RegistrationView registrationView;
    private final RegistrationService registrationService;

    public RegistrationController(ServiceDelegator serviceDelegator, RegistrationView registrationView) throws ClassNotFoundException {
        this.registrationView = registrationView;
        registrationService = (RegistrationService) serviceDelegator.getByClass(RegistrationService.class);
    }

    public void start() {
        do {
            RegistrationAction action = registrationView.chooseAction();
            switch (action) {
                case LOGIN -> login();
                case REGISTER -> register();
                case GO_BACK -> start();
            }
        } while (CurrentSession.getRole().equals(UserRole.NOT_AUTHORIZED));
    }

    public void login() {
        registrationView.login();
        String email = registrationView.getEmail();
        String password = registrationView.getLoginPassword();
        if (!registrationService.login(email, password))
            registrationView.errorLogin();
    }

    public void register() {
        registrationView.register();
        String email = registrationView.getEmail();
        String password = registrationView.getRegisterPassword();
        UserRole role = registrationView.getRole();
        String name = registrationView.getName();
        String lastname = registrationView.getLastname();
        if (!registrationService.register(email, password, role, name, lastname))
            registrationView.errorRegister();
    }
}
