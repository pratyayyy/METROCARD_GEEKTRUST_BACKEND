package com.geektrust.backend.services;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.repositories.IMetroCardRepository;



public class MetroCardService implements IMetroCardService
{
    private final IStationService stationService;
    private final IMetroCardRepository metroCardRepository;

    public MetroCardService(IStationService stationService,IMetroCardRepository metroCardRepository)
    {
         this.stationService=stationService;
         this.metroCardRepository=metroCardRepository;
    }

    @Override
    public MetroCard create(String cardNumber,int balance)
    {
       Optional<MetroCard> card =  metroCardRepository.findByCardNumber(cardNumber);
       
        if(card.isPresent()) 
        {
            MetroCard metroCard = card.get();
            return metroCard;
        }
        else 
        {
            MetroCard metroCard = new MetroCard(cardNumber, balance);
            return metroCardRepository.save(metroCard);
        }
    }
                                                                                                        
    @Override
    public void recharge(MetroCard metroCard, int travelFare, Passenger passenger)
    {
     int balance = metroCard.getBalance();
     int recharge = travelFare - balance;
     metroCard.addAmount(recharge);
     stationService.collectServiceFee(passenger,recharge);
    }

    @Override
    public void payment(MetroCard metroCard, int travelFare, Passenger passenger)
    {
        metroCard.deductAmount(travelFare);
        stationService.collectTravelFare(passenger, travelFare);

    }


}

