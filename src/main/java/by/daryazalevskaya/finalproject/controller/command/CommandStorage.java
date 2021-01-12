package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.get.RegisterGetCommand;
import by.daryazalevskaya.finalproject.controller.command.post.ChangeLocaleCommand;
import by.daryazalevskaya.finalproject.controller.command.post.RegisterPostCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandStorage {
    private static CommandStorage storage;


    private static Map<UriPattern, ActionCommand> getRequestMap = new EnumMap<>(UriPattern.class);
    private static Map<UriPattern, ActionCommand> postRequestMap = new EnumMap<>(UriPattern.class);

    private CommandStorage() {
        getRequestMap.put(UriPattern.REGISTRATION, new RegisterGetCommand());

        postRequestMap.put(UriPattern.REGISTRATION, new RegisterPostCommand());
        getRequestMap.put(UriPattern.CHANGE_LOCALE, new ChangeLocaleCommand());
        //TODO fill maps
    }

    public static CommandStorage getInstance() {
        if (storage == null) {
            storage = new CommandStorage();
        }
        return storage;
    }

    public ActionCommand getCommandPost(UriPattern pattern) {
        return postRequestMap.get(pattern);
    }

    public ActionCommand getCommandGet(UriPattern pattern) {
        return getRequestMap.get(pattern);
    }
}
