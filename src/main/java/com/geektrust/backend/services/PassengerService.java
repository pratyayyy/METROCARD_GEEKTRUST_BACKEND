  package com.geektrust.backend.services;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.exceptions.MetroCardNotFoundException;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;

public class PassengerService implements IPassengerService{
    private final IStationService stationService;
    private final IMetroCardService metroCardService;
    private final IMetroCardRepository metroCardRepository;
    private final IPassengerRepository passengerRepository;

    public PassengerService(IStationService stationService,IMetroCardService metroCardService,IMetroCardRepository metroCardRepository,IPassengerRepository passengerRepository)
    {
      this.stationService=stationService;
      this.metroCardService=metroCardService;
      this.metroCardRepository=metroCardRepository;
      this.passengerRepository=passengerRepository;
    }

    @Override
    public Passenger create(String cardNumber,PassengerType passengerType,String boardingStation)
    {
        MetroCard metroCard = metroCardRepository.findByCardNumber(cardNumber).orElseThrow(()-> new MetroCardNotFoundException());
        Optional<Passenger> passengercheck = passengerRepository.findByMetroCard(metroCard);

        if(passengercheck.isPresent())
        {
             Passenger passenger = passengercheck.get();
             passenger.setBoardingStation(boardingStation);
             return passenger;
        }
        else
        {
          Passenger passenger = new Passenger(metroCard, passengerType, boardingStation);
          return passengerRepository.save(passenger);
        }
    }

    @Override
    public void travel(Passenger passenger)
    {
      MetroCard metroCard = passenger.getMetroCard();
      passenger.JourneyCodeUpdate();
      int travelFare = stationService.getTravelFare(passenger);

      if(metroCard.checkSufficientBalance(travelFare))
      {
        metroCardService.payment(metroCard, travelFare, passenger);
      }
      else
      {
        metroCardService.recharge(metroCard, travelFare, passenger);
        metroCardService.payment(metroCard, travelFare, passenger);
      }
      stationService.addPassengerToList(passenger);
    }  
}
