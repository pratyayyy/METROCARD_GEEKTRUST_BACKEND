package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;


public interface IMetroCardService {
    public MetroCard create(String cardNo, int balance);
    public void recharge(MetroCard metroCard, int travelFare,Passenger passenger);
    public void payment(MetroCard metroCard, int travelFare,Passenger passenger);    
}
