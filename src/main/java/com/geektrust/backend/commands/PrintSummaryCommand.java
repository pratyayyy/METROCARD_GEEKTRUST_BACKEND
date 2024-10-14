package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.dto.CollectionSummary;
import com.geektrust.backend.dto.PassengerSummary;
import com.geektrust.backend.dto.PassengerTypeSummary;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.services.IStationService;

public class PrintSummaryCommand implements ICommand{

    private final IStationService stationService;

    public PrintSummaryCommand(IStationService stationService) 
    {
        this.stationService = stationService;
    }

    @Override
    public void execute(List<String> tokens) {
        List<Station> stations = stationService.getAllStations();

        for(Station station : stations)
        {
            CollectionSummary collectionSummary = stationService.getCollectionSummary(station);
            System.out.println("TOTAL_COLLECTION " + collectionSummary.getStationName() + " " + collectionSummary.getTotalCollection() + " " + 
            collectionSummary.getDiscountCollection());   

            PassengerSummary passengerSummary = stationService.getPassengerSummary(station);
            List<PassengerTypeSummary> passengerTypeCounts = passengerSummary.getPassengerTypeSummary();
    
            System.out.println("PASSENGER_TYPE_SUMMARY");
    
            for(PassengerTypeSummary passengerTypeCount : passengerTypeCounts)
                System.out.println(passengerTypeCount.getPassengerType() + " " + passengerTypeCount.getCount());

        }
    } 
        
}
    

