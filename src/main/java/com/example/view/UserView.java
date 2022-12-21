package com.example.view;

import com.example.controllers.UserAction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserView {

    private final Scanner scanner = new Scanner(System.in);

    public UserAction chooseAction() {
        UserAction action = null;
        try {
            System.out.println("Choose action:");
            Arrays.stream(UserAction.values()).forEach(System.out::println);
            action = UserAction.valueOf((scanner.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such action available.");
        }
        return action;
    }

    public void errorMessage() {
        System.out.println("Something gone wrong.");
    }

    public void print (String message) {
        System.out.println(message);
    }

    public int getDuration() {
        int duration = 0;
        try {
            System.out.print("Enter the amount of time: ");
            duration = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Problem with reading the number occurred.");
        }
        return duration;
    }

    public Long getActivityId() {
        Long id = null;
        try {
            System.out.print("Enter activity id: ");
            id = Long.valueOf((scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Problem with reading the number occurred.");
        }
        return id;
    }

    public <T> void printList(String listName, List<T> list) {
        System.out.println(listName);
        System.out.println(list);
    }

    public void signOut() {
        System.out.println("You are signed out.");
    }

    public String getCategoryName () {
        System.out.println("Enter category name");
        return scanner.nextLine();
    }

}
