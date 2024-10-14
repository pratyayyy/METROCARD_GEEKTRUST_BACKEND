package com.geektrust.backend.repositories;

import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public interface IMetroCardRepository {
    public MetroCard save(MetroCard cardentity);
    public Optional<MetroCard> findByCardNumber(String cardNumber);
    
}
