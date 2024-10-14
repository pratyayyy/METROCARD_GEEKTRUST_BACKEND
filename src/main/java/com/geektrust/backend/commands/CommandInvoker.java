package com.geektrust.backend.commands;

import java.util.HashMap;
import java.util.List;

public class CommandInvoker {
    private static final HashMap<String, ICommand> commandMap = new HashMap<>();
    public void register(String commandName, ICommand command) 
    {
        commandMap.put(commandName,command);
    }

    private ICommand getCommand(String commandName) 
    {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) 
    {
        ICommand command = getCommand(commandName);
        command.execute(tokens);
    }   
}
