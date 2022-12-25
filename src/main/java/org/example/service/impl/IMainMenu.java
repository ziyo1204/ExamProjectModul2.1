package org.example.service.impl;

import org.example.Storage;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.interfaces.MainMenu;

import java.util.Scanner;

public class IMainMenu implements MainMenu {
    private static MainMenu mainMenu;

    public static MainMenu getInstance() {
        if (mainMenu == null) {
            mainMenu = new IMainMenu();
        }
        return mainMenu;
    }
    @Override
    public void startProject() {
        User user = new User(User.getCurrentId(), "Adminbek", "admin", "12", Role.ADMIN, 0);
        Storage.users.add(user);
        System.out.println("Xush kelibsiz!");
        first:
        while (true) {
            System.out.println("'1'-\"Kirish\" '2'-\"Ro'yxatdan o'tish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            switch (com) {
                case "1":
                    ISignInSignUp.getInstance().signIn();
                    break;
                case "2":
                    ISignInSignUp.getInstance().signUp();
                    break;
                case "0":
                    break first;
                default:
                    System.out.println("Mavjud bo'lmagan buyruq!");
                    break;
            }
        }
    }
}
