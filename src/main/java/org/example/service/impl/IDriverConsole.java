package org.example.service.impl;

import org.example.Storage;
import org.example.model.Travel;
import org.example.model.User;
import org.example.service.interfaces.DriverConsole;

import java.util.Scanner;

public class IDriverConsole implements DriverConsole {
    private static DriverConsole driverConsole;
    public static DriverConsole getInstance(){
        if(driverConsole==null){
            driverConsole=new IDriverConsole();
        }
        return driverConsole;
    }
    @Override
    public void driverConsole(User currentUser) {
        System.out.println("Welcome " + currentUser.getName() + "!");
        label:
        while (true) {
            System.out.println("'1'-\"Sayohatlarni ko'rish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            switch (com) {
                case "1":
                    viewTravel(currentUser);
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Error!");
                    break;
            }
        }
    }

    @Override
    public void viewTravel(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                System.out.println("= - = - = - = - = - = - = - = - =");
                System.out.println("= - = - = - Travels = - = - = - =");
                System.out.println("= - = - = - = - = - = - = - = - =");
                for (Travel travel : Storage.travels) {
                    if (travel.getUser().equals(currentUser)){
                        System.out.println(travel);
                        System.out.println("= - = - = - = - = - = - = - = - =");
                    }
                }
            }
        }
    }
}
