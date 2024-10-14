package com.geektrust.backend.services;

import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;

public interface IPassengerService {
    public Passenger create(String cardNo, PassengerType passengerType, String boardingStation);
    public void travel(Passenger passenger);
}
