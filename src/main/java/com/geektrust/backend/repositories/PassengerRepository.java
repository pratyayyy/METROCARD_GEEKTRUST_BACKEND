package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;

public class PassengerRepository implements IPassengerRepository {
    private final HashMap<String,Passenger> passengerMap;
    int counter = 0;
    
    public PassengerRepository(HashMap<String,Passenger> passengerMap)
    {
         this.passengerMap=passengerMap;
         this.counter = passengerMap.size();
    }

    public PassengerRepository()
    {
        this.passengerMap=new HashMap<>();
    }

    
    @Override
    public Passenger save(Passenger passengerentity)
    {    
        if( passengerentity.getId() == null)
        {
            counter++;
            Passenger passenger = new Passenger(Integer.toString(counter), passengerentity.getMetroCard(), passengerentity.getPassengerType(), passengerentity.getBoardingStation());
            passengerMap.put(passenger.getId(), passenger);
            return passenger;
        }
        passengerMap.put(passengerentity.getId(), passengerentity);
        return passengerentity;
    }

    @Override
    public Optional<Passenger>findByMetroCard( MetroCard metroCard)
    {
     Optional<Passenger> passenger = passengerMap.values().stream().filter(p->p.getMetroCard().equals(metroCard)).findFirst();
     return passenger;
    }
    
}
