package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public class MetroCardRepository implements IMetroCardRepository{

    private final HashMap<String,MetroCard> metroCardMap;
    private int counter = 0;
  
    
    public MetroCardRepository(HashMap<String,MetroCard> metroCardMap)
    {
        this.metroCardMap=metroCardMap;
        this.counter = metroCardMap.size();
      
    } 
    public MetroCardRepository()
    {
        this.metroCardMap=new HashMap<String,MetroCard>();
    }

    @Override
    public MetroCard save(MetroCard cardentity)
    {    
        if(cardentity.getId()==null)
        {
            counter++;
            MetroCard metroCard = new MetroCard(Integer.toString(counter), cardentity.getCardNumber(), cardentity.getBalance());
            metroCardMap.put(metroCard.getId(),metroCard);
            return metroCard;
        }
        metroCardMap.put(cardentity.getId(),cardentity);
        return cardentity;
    }


    @Override
    public Optional<MetroCard> findByCardNumber(String cardNumber)
    {
        Optional<MetroCard> findCard = metroCardMap.values().stream().filter(card -> card.getCardNumber().equals(cardNumber)).findFirst();
        return findCard;
    }   
    
}
