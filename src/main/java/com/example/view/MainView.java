package com.example.view;

import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);

    public boolean ifLeave() {
        String choice;
        do {
            System.out.println("Do you want to leave? YES / NO");
            choice = scanner.nextLine();
        } while (!choice.equals("YES") && !choice.equals("NO"));
        return choice.equals("YES");
    }
}
