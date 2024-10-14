package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.IMetroCardService;

public class BalanceCommand implements ICommand{
    private final  IMetroCardService metroCardService;

    
    public BalanceCommand(IMetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }
    
    @Override
    public void execute(List<String> tokens) {
       
        String cardNumber = tokens.get(1);
        int balance = Integer.valueOf(tokens.get(2));
        metroCardService.create(cardNumber, balance);
    } 
}
