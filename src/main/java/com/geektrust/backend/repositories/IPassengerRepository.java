package com.geektrust.backend.repositories;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;

public interface IPassengerRepository {
    public Passenger save(Passenger passengerentity);
    public Optional<Passenger> findByMetroCard( MetroCard metroCard);
    
}
