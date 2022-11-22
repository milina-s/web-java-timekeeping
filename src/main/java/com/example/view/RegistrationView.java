package com.example.view;

import com.example.controllers.RegistrationAction;
import com.example.entities.UserRole;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class RegistrationView {

    private final Scanner scanner = new Scanner(System.in);

    public RegistrationAction chooseAction() {
        RegistrationAction action;
        System.out.println("Choose action:");
        Arrays.stream(RegistrationAction.values()).forEach(System.out::println);
        action = RegistrationAction.getAction(scanner.nextLine());
        return action;
    }

    public void errorMessage() {
        System.out.println("Something gone wrong.");
    }

    public void login() {
        System.out.println("Logging in:");
    }

    public void register() {
        System.out.println("Registration:");
    }

    public String getEmail() {
        System.out.print("Email: ");
        return scanner.nextLine();
    }

    public UserRole getRole() {
        UserRole role = UserRole.NOT_AUTHORIZED;
        do {
            System.out.print("Choose role: ");
            System.out.println("USER or ADMIN");
            role = UserRole.getUserRole(scanner.nextLine());
        } while (!role.equals(UserRole.USER) && !role.equals(UserRole.ADMIN));
        return role;
    }

    public String getName() {
        System.out.print("Name: ");
        return scanner.nextLine();
    }

    public String getLastname() {
        System.out.print("Lastname: ");
        return scanner.nextLine();
    }

    public String getLoginPassword() {
        System.out.print("Password: ");
        return scanner.nextLine();
    }

    public String getRegisterPassword() {
        String password;
        do {
            System.out.print("Password: ");
            password = scanner.nextLine();
            System.out.print("Repeat password: ");
        } while (!Objects.equals(password, scanner.nextLine()));
        return password;
    }

    public void errorLogin() {
        System.out.println("Wrong email or password");
    }

    public void errorRegister() {
        System.out.println("Something gone wrong during registration");
    }
}
