package org.example;

import org.example.model.User;
import org.example.service.impl.IMainMenu;

public class Application {
    public static void main(String[] args) {
        IMainMenu.getInstance().startProject();
    }
}