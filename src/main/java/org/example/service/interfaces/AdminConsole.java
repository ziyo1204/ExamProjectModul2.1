package org.example.service.interfaces;

import org.example.model.User;

public interface AdminConsole {
    void adminConsole(User currentUser);
    void addBus();
    void addDriver();
    void addTravel();
    void viewBusPosition();
}
