package org.example.service.impl;

import org.example.Storage;
import org.example.model.*;
import org.example.service.interfaces.AdminConsole;

import java.util.List;
import java.util.Scanner;

public class IAdminConsole implements AdminConsole {
    private static AdminConsole adminConsole;

    public static AdminConsole getInstance() {
        if (adminConsole == null) {
            adminConsole = new IAdminConsole();
        }
        return adminConsole;
    }
    @Override
    public void adminConsole(User currentUser) {
        System.out.println("Welcome " + currentUser.getName() + "!");
        label:
        while (true) {
            System.out.println("'1'-\"Yangi avtobus qo'shish\" '2'-\"Yangi haydovchi qo’shish\" " +
                    "'3'-\"Yangi qatnov(sayoxat) yaratish\" '4'-\"Avtobuslardagi holatni tekshirish\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            switch (com) {
                case "1":
                    addBus();
                    break;
                case "2":
                    addDriver();
                    break;
                case "3":
                    addTravel();
                    break;
                case "4":
                    viewBusPosition();
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
    public void addBus() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                scanner = new Scanner(System.in);
                System.out.print("Bus name: ");
                String name = scanner.nextLine();
                System.out.print("Bus number: ");
                String number = scanner.nextLine();
                System.out.print("Number of seats: ");
                scanner = new Scanner(System.in);
                int numberOfSeats = scanner.nextInt();
                Bus bus = Storage.buses.stream().filter(a ->
                        a.getName().equals(name) && a.getNumber().equals(number) &&
                                a.getNumberOfSeats() == numberOfSeats).findFirst().orElse(null);
                if (bus!=null){
                    System.out.println("Bunday avtobus oldin qo'shilgan!");
                }else {
                    Bus bus1 = new Bus(Bus.getCurrentId(), name, number, numberOfSeats);
                    Storage.buses.add(bus1);
                    System.out.println("Avtobus muvaffaqqiyatli qo'shildi!");
                }
            }
        }
    }

    @Override
    public void addDriver() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                scanner = new Scanner(System.in);
                System.out.print("login: ");
                String login = scanner.nextLine();
                User user=Storage.users.stream().filter(user1 ->
                        user1.getLogin().equals(login)).findFirst().orElse(null);
                if (user!=null){
                    System.out.println("Bu login registratsiyadan o'tgan!");
                }else {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    user= new User(User.getCurrentId(), name, login, password, Role.DRIVER, 0);
                    Storage.users.add(user);
                    System.out.println("Registratsiya muvaffaqqiyatli yakunlandi!");
                }
            }
        }
    }

    @Override
    public void addTravel() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                scanner = new Scanner(System.in);
                //Travell(id, name, from, to, date, bus, user(haydovchi), priceForPerSeat)
                System.out.print("Travel name: ");
                String name = scanner.nextLine();
                System.out.print("From: ");
                String from = scanner.nextLine();
                System.out.print("To: ");
                String to = scanner.nextLine();
                System.out.print("Date: ");
                String date = scanner.nextLine();
                scanner = new Scanner(System.in);
                System.out.print("Price for per seat: ");
                int priceForPerSeat = scanner.nextInt();
                if (Storage.buses.size()==0){
                    System.out.println("Avtobuslar mavjud emas, oldin avtobus qo'shing!");
                    break;
                }
                System.out.println("= - = - = - = - = - = - = - = - =");
                System.out.println("= - = - = - = Buses = - = - = - =");
                System.out.println("= - = - = - = - = - = - = - = - =");
                for (Bus bus : Storage.buses) {
                    System.out.println(bus);
                    System.out.println("= - = - = - = - = - = - = - = - =");
                }
                System.out.print("Bus id: ");
                int id = scanner.nextInt();
                Bus bus = Storage.buses.stream().filter(bus1 -> bus1.getId() == id).findFirst().orElse(null);
                if(bus==null){
                    System.out.println("Mavjud bo'lmagan id!");
                    break;
                }else {
                    if (isExist(Storage.users)){
                        System.out.println("Haydovchilar mavjud emas, oldin haydovchi qo'shing!");
                        break;
                    }
                    System.out.println("= - = - = - = - = - = - = - = - =");
                    System.out.println("= - = - = - Drivers = - = - = - =");
                    System.out.println("= - = - = - = - = - = - = - = - =");
                    for (User user : Storage.users) {
                        if (user.getRole().equals(Role.DRIVER)){
                            System.out.println(user);
                            System.out.println("= - = - = - = - = - = - = - = - =");
                        }
                    }
                    System.out.print("Driver id: ");
                    int id1 = scanner.nextInt();
                    User driver =Storage.users.stream().filter(user1 ->
                            user1.getId()==id1).findFirst().orElse(null);
                    if(driver==null){
                        System.out.println("Mavjud bo'lmagan id!");
                        break;
                    }else {
                        Travel travel = new Travel(Travel.getCurrentId(), name, from, to, date, bus, driver, priceForPerSeat);
                        Storage.travels.add(travel);
                        //Ticket(id, travell, price, seatNumber, status(available, sold))
                        for (int i = 1; i <= bus.getNumberOfSeats(); i++) {
                            Ticket ticket = new Ticket(Ticket.getCurrentId(), travel, priceForPerSeat, i, Status.AVAILABLE);
                            Storage.tickets.add(ticket);
                        }
                        System.out.println("Travel muvaffaqqiyatli qo'shildi!");
                    }
                }
            }
        }
    }
    public boolean isExist(List<User> list){
        User user = Storage.users.stream().filter(user1 ->
                user1.getRole().equals(Role.DRIVER)).findFirst().orElse(null);
        return user==null;
    }

    @Override
    public void viewBusPosition() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if(com==0){
                break;
            }else {
                System.out.println("= - = - = - = - = - = - = - = - =");
                System.out.println("= - = - = - = Buses = - = - = - =");
                System.out.println("= - = - = - = - = - = - = - = - =");
                for (Bus bus : Storage.buses) {
                    System.out.println(bus);
                    System.out.println("= - = - = - = - = - = - = - = - =");
                }
                System.out.print("Bus id: ");
                int id = scanner.nextInt();
                Bus bus = Storage.buses.stream().filter(bus1 -> bus1.getId() == id).findFirst().orElse(null);
                if(bus==null){
                    System.out.println("Mavjud bo'lmagan id!");
                    break;
                }else {
                    System.out.println("Unga biriktirilgan travel:");
                    for (Travel travel : Storage.travels) {
                        if(travel.getBus().equals(bus)){
                            System.out.println(travel);
                            System.out.println("Band qilingan joylari:");
                            for (Ticket ticket : Storage.tickets) {
                                if (ticket.getTravel().equals(travel)&&ticket.getStatus().equals(Status.SOLD)){
                                    System.out.println(" Ticket id: "+ticket.getId()+
                                            "\n Ticket price: "+ticket.getPrice()+
                                            "\n O'rindiq raqami: "+ticket.getSeatNumber()+
                                            "\n Statusi: "+ticket.getStatus());
                                    System.out.println("= - = - = - = - = - = - = - = - =");
                                }
                            }
                            System.out.println("Band bo'lmagan joylari:");
                            for (Ticket ticket : Storage.tickets) {
                                if (ticket.getTravel().equals(travel)&&ticket.getStatus().equals(Status.AVAILABLE)){
                                    System.out.println(" Ticket id: "+ticket.getId()+
                                            "\n Ticket price: "+ticket.getPrice()+
                                            "\n O'rindiq raqami: "+ticket.getSeatNumber()+
                                            "\n Statusi: "+ticket.getStatus());
                                    System.out.println("= - = - = - = - = - = - = - = - =");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
