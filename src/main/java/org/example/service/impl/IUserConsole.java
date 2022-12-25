package org.example.service.impl;

import org.example.Storage;
import org.example.model.*;
import org.example.service.interfaces.UserConsole;

import java.util.Scanner;

public class IUserConsole implements UserConsole {
    private static UserConsole userConsole;

    public static UserConsole getInstance() {
        if (userConsole == null) {
            userConsole = new IUserConsole();
        }
        return userConsole;
    }

    @Override
    public void userConsole(User currentUser) {
        System.out.println("Welcome " + currentUser.getName() + "!");
        label:
        while (true) {
            System.out.println("'1'-\"Bilet sotib olish\" '2'-\"Biletlar tarixi\" '0'-\"Chiqish\"");
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            switch (com) {
                case "1":
                    buyTicket(currentUser);
                    break;
                case "2":
                    viewIstory(currentUser);
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
    public void buyTicket(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {
                scanner = new Scanner(System.in);
                System.out.println("= - = - = - = - = - = - = - = - =");
                System.out.println("= - = - = - Travels = - = - = - =");
                System.out.println("= - = - = - = - = - = - = - = - =");
                for (Travel travel : Storage.travels) {
                    System.out.println(travel);
                    System.out.println("= - = - = - = - = - = - = - = - =");
                }
                System.out.print("Travel id: ");
                int id = scanner.nextInt();
                Travel travel = Storage.travels.stream().filter(travel1 ->
                        travel1.getId() == id).findFirst().orElse(null);
                if (travel == null) {
                    System.out.println("Mavjud bo'lmagan id!");
                    break;
                } else {
                    System.out.println("Unga biriktirilgan avtobus:");
                    for (Bus bus : Storage.buses) {
                        if (travel.getBus().equals(bus)){
                            System.out.println(bus);
                            System.out.println("= - = - = - = - = - = - = - = - =");
                            System.out.println("Mavjud biletlar:");
                            for (Ticket ticket : Storage.tickets) {
                                if (ticket.getTravel().equals(travel)&&ticket.getStatus().equals(Status.AVAILABLE)){
                                    System.out.println(" Ticket id: "+ticket.getId()+
                                            "\n Ticket price: "+ticket.getPrice()+
                                            "\n O'rindiq raqami: "+ticket.getSeatNumber());
                                    System.out.println("= - = - = - = - = - = - = - = - =");
                                }
                            }
                            System.out.println("Bilet id:");
                            int id1 = scanner.nextInt();
                            Ticket ticket = Storage.tickets.stream().filter(ticket1 ->
                                    ticket1.getId()==id1).findFirst().orElse(null);
                            if (ticket==null){
                                System.out.println("Mavjud bo'lmagan id!");
                                break;
                            }else {
                                Order order = new Order(Order.getCurrentId(), currentUser, ticket, ticket.getPrice());
                                ticket.setStatus(Status.SOLD);
                                Storage.orders.add(order);
                                System.out.println("Bilet muvaffaqqiyatli sotib olindi!");
                            }
                        }
                    }
                }
            }
        }
    }
    //Order(id, user(foydalanuvchi), ticket, sum)
    @Override
    public void viewIstory(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("'1'-\"Davom etish\" '0'-\"Chiqish\"");
            int com = scanner.nextInt();
            if (com == 0) {
                break;
            } else {

                System.out.println("Sotib olgan biletlarim:");
                for (Order order : Storage.orders) {
                    if (order.getUser().equals(currentUser)){
                        System.out.println(" Ticket id: "+order.getTicket().getId()+
                                "\n Ticket price: "+order.getTicket().getPrice()+
                                "\n O'rindiq raqami: "+order.getTicket().getSeatNumber()+
                                "\n Statusi: "+order.getTicket().getStatus());
                        System.out.println("= - = - = - = - = - = - = - = - =");
                    }
                }
            }
        }
    }
}
