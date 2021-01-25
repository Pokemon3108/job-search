package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;

public class CommandManagerFactory {
    public static CommandManager getManager(ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
