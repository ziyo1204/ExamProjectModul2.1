package org.example.service.impl;

import org.example.Storage;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.interfaces.SignInSignUp;

import java.util.Scanner;

public class ISignInSignUp implements SignInSignUp {
    private static SignInSignUp signInSignUp;

    public static SignInSignUp getInstance() {
        if (signInSignUp == null) {
            signInSignUp = new ISignInSignUp();
        }
        return signInSignUp;
    }
    @Override
    public void signIn() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Login: ");
            String login = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            User user = Storage.users.stream().filter(user1 ->
                            user1.getLogin().equals(login) && user1.getPassword().equals(password))
                    .findFirst().orElse(null);
            if (user == null) {
                System.out.println("Kirish uchun registratsiyadan o'ting!");
                break;
            }else {
                if(user.getRole().equals(Role.ADMIN)){
                    IAdminConsole.getInstance().adminConsole(user);
                    break;
                }
                if(user.getRole().equals(Role.DRIVER)){
                    IDriverConsole.getInstance().driverConsole(user);
                    break;
                }
                if(user.getRole().equals(Role.USER)){
                    IUserConsole.getInstance().userConsole(user);
                    break;
                }
            }
        }
    }

    @Override
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Login yarating: ");
            String login = scanner.nextLine();
            User user = Storage.users.stream().filter(user1 ->
                    user1.getLogin().equals(login)).findFirst().orElse(null);
            if(user!=null){
                System.out.println("Bu login registratsiyadan o'tgan!");
            }else {
                System.out.print("Name kiriting: ");
                String name = scanner.nextLine();
                System.out.print("Password yarating: ");
                String password = scanner.nextLine();
                User user1 = new User(User.getCurrentId(), name, login, password, Role.USER, 1000000);
                Storage.users.add(user1);
                System.out.println("Registratsiya muvaffaqqiyatli yakunlandi!");
                System.out.println("Bonus sifatida boshlang'ich balance 1 mln so'm");
                break;
            }
        }
    }
}
