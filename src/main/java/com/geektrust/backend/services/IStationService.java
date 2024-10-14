package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.dto.CollectionSummary;
import com.geektrust.backend.dto.PassengerSummary;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.Station;

public interface IStationService {
    public Station create(String stationName);
    public void addPassengerToList(Passenger passenger);
    public void collectTravelFare(Passenger passenger, int travelFare);
    public void collectServiceFee(Passenger passenger, int amount);
    public List<Station> getAllStations();
    public int getTravelFare(Passenger passenger);
    public CollectionSummary getCollectionSummary(Station station);
    public PassengerSummary getPassengerSummary(Station station);
}
