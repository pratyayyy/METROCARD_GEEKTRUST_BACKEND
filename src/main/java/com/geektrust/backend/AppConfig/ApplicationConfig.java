package com.geektrust.backend.AppConfig;

import com.geektrust.backend.commands.BalanceCommand;
import com.geektrust.backend.commands.CheckInCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.PassengerRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.IPassengerService;
import com.geektrust.backend.services.IStationService;
import com.geektrust.backend.services.MetroCardService;
import com.geektrust.backend.services.PassengerService;
import com.geektrust.backend.services.StationService;

public class ApplicationConfig {

    private final IMetroCardRepository metroCardRepository = new MetroCardRepository();
    private final IPassengerRepository passengerRepository = new PassengerRepository();
    private final IStationRepository stationRepository = new StationRepository();

    private final IStationService stationService = new StationService(stationRepository);
    private final IMetroCardService metroCardService = new MetroCardService(stationService, metroCardRepository);
    private final IPassengerService passengerService = new PassengerService(stationService, metroCardService, metroCardRepository, passengerRepository);

    private final BalanceCommand balanceCommand = new BalanceCommand(metroCardService);
    private final CheckInCommand checkInCommand = new CheckInCommand(passengerService, stationService);
    private final PrintSummaryCommand printSummaryCommand = new PrintSummaryCommand(stationService);


    private final CommandInvoker commandInvoker = new CommandInvoker();
    
    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("BALANCE", balanceCommand);
        commandInvoker.register("CHECK_IN", checkInCommand);
        commandInvoker.register("PRINT_SUMMARY", printSummaryCommand);

     return commandInvoker;
    }
    
}
