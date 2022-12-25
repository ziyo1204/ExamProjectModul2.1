package org.example.model;

import java.util.Objects;

public class Travel {
    //Travell(id, name, from, to, date, bus, user(haydovchi), priceForPerSeat)
    private int id;
    private String name;
    private String from;
    private String to;
    private String data;
    private Bus bus;
    private User user;
    private int priceForPerSeat;
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public static int currentId=0;
    {
        currentId++;
    }

    public Travel(int id, String name, String from, String to, String data, Bus bus, User user, int priceForPerSeat) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.to = to;
        this.data = data;
        this.bus = bus;
        this.user = user;
        this.priceForPerSeat = priceForPerSeat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Bus getBus() {
        return bus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPriceForPerSeat() {
        return priceForPerSeat;
    }

    public void setPriceForPerSeat(int priceForPerSeat) {
        this.priceForPerSeat = priceForPerSeat;
    }

    public static int getCurrentId() {
        return currentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return id == travel.id && priceForPerSeat == travel.priceForPerSeat && Objects.equals(name, travel.name) && Objects.equals(from, travel.from) && Objects.equals(to, travel.to) && Objects.equals(data, travel.data) && Objects.equals(bus, travel.bus) && Objects.equals(user, travel.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, from, to, data, bus, user, priceForPerSeat);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", data='" + data + '\'' +
                ", bus=" + bus +
                ", driver=" + user.getName() +
                ", priceForPerSeat=" + priceForPerSeat +
                '}';
    }
}
