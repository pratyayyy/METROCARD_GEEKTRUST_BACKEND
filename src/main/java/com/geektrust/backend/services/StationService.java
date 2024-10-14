package com.geektrust.backend.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.dto.CollectionSummary;
import com.geektrust.backend.dto.PassengerSummary;
import com.geektrust.backend.dto.PassengerTypeSummary;
import com.geektrust.backend.entities.JourneyType;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.TravelFare;
import com.geektrust.backend.exceptions.StationNotFoundException;
import com.geektrust.backend.repositories.IStationRepository;

public class StationService implements IStationService {
    private final IStationRepository stationRepository;
    
    public StationService(IStationRepository stationRepository)
    {
       this.stationRepository = stationRepository;
    }

    @Override
    public Station create(String stationName)
    {  
        Optional<Station> st = stationRepository.findByStationName(stationName);
        if(st.isPresent())
        {
            Station station = st.get();
            return station;
        }
        else
        {
            Station station = new Station(stationName);
            return stationRepository.save(station);
        }
    }

    @Override
    public void addPassengerToList(Passenger passenger) 
    {
        String stationName = passenger.getBoardingStation();
        Station station = stationRepository.findByStationName(stationName).orElseThrow(() -> new StationNotFoundException());
        station.addPassenger(passenger);
    }

    @Override
    public void collectTravelFare(Passenger passenger, int travelFare)
    {
        String stationName = passenger.getBoardingStation();
        Station station = stationRepository.findByStationName(stationName).orElseThrow(() -> new StationNotFoundException());
        station.addTravelFare(travelFare);
    }

    @Override
    public void collectServiceFee(Passenger passenger, int amount)
    {
        String stationName = passenger.getBoardingStation();
        Station station = stationRepository.findByStationName(stationName).orElseThrow(() -> new StationNotFoundException());
        int servicefee=(int)(0.02 * amount);
        station.addServiceFee(servicefee);
    }


    @Override
    public List<Station> getAllStations()
    {
        List<Station> stations = stationRepository.findAll();
        Collections.sort(stations);
        return stations;
    }

    @Override
    public int getTravelFare(Passenger passenger)
    {
        String passengerType = passenger.getPassengerType().toString();
        int travelFare = TravelFare.valueOf(passengerType).getFare();
        int journeyTypeCode = passenger.getJourneyCode();
        JourneyType journeyType;
        if(journeyTypeCode == 0)
        {
              journeyType = JourneyType.SINGLE_TRIP;
        }
        else
        {
               journeyType = JourneyType.RETURN;
        }
       

        if(journeyType.equals(JourneyType.SINGLE_TRIP))
            return travelFare;
        else
        {
            int revisedTravelFare = (int)(0.5 * travelFare);
            int discount = travelFare - revisedTravelFare;
            String stationName = passenger.getBoardingStation();
            Station station = stationRepository.findByStationName(stationName).orElseThrow(() -> new StationNotFoundException());
            station.addDiscount(discount);
            return revisedTravelFare;
        }
    }

    @Override
    public CollectionSummary getCollectionSummary(Station station) 
    {
        int totalCollection = station.getTotalCollection();
        int discountCollection = station.getTotalDiscount();
        CollectionSummary collectionSummary = new CollectionSummary(station.getName(), totalCollection, discountCollection);
        return collectionSummary;
    }

    @Override
    public PassengerSummary getPassengerSummary(Station station)
    {
        List<Passenger> passengers = station.getPassengers();
        Map<PassengerType, Integer> passengerTypeSummaryMap = new HashMap<>();

        for(Passenger passenger : passengers)
        {
            PassengerType passengerType = passenger.getPassengerType();
            int count = passengerTypeSummaryMap.getOrDefault(passengerType, 0);
            count++;
            passengerTypeSummaryMap.put(passengerType, count);
        }

        List<PassengerTypeSummary> passengerTypeList = passengerTypeSummaryMap.entrySet().stream().map(entry -> new PassengerTypeSummary(entry.getKey(), entry.getValue())).sorted().collect(Collectors.toList());
       
        PassengerSummary passengerSummary = new PassengerSummary(passengerTypeList);
        return passengerSummary;
   
    }


}   
