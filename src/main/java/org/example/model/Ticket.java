package org.example.model;

import java.util.Objects;

public class Ticket {
    //Ticket(id, travell, price, seatNumber, status(available, sold))
    private int id;
    private Travel travel;
    private int price;
    private int seatNumber;
    private Status status;
    public static int currentId=0;
    {
        currentId++;
    }

    public Ticket(int id, Travel travel, int price, int seatNumber, Status status) {
        this.id = id;
        this.travel = travel;
        this.price = price;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static int getCurrentId() {
        return currentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && price == ticket.price && seatNumber == ticket.seatNumber && Objects.equals(travel, ticket.travel) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, travel, price, seatNumber, status);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", travel=" + travel +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                ", status=" + status +
                '}';
    }
}
