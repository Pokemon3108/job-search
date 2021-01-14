package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.get.EmployerHomeCommand;
import by.daryazalevskaya.finalproject.controller.command.get.LoginGetCommand;
import by.daryazalevskaya.finalproject.controller.command.get.RegisterGetCommand;
import by.daryazalevskaya.finalproject.controller.command.get.ResumeGetCommand;
import by.daryazalevskaya.finalproject.controller.command.post.ChangeLocaleCommand;
import by.daryazalevskaya.finalproject.controller.command.post.LoginPostCommand;
import by.daryazalevskaya.finalproject.controller.command.post.LogoutCommand;
import by.daryazalevskaya.finalproject.controller.command.post.RegisterPostCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandStorage {
    private static CommandStorage storage;


    private static Map<UriPattern, ActionCommand> getRequestMap = new EnumMap<>(UriPattern.class);
    private static Map<UriPattern, ActionCommand> postRequestMap = new EnumMap<>(UriPattern.class);

    private CommandStorage() {
        getRequestMap.put(UriPattern.REGISTRATION, new RegisterGetCommand());
        getRequestMap.put(UriPattern.CHANGE_LOCALE, new ChangeLocaleCommand());
        getRequestMap.put(UriPattern.LOGIN, new LoginGetCommand());
        getRequestMap.put(UriPattern.EMPLOYEE_HOME, new ResumeGetCommand());
        getRequestMap.put(UriPattern.EMPLOYER_HOME, new EmployerHomeCommand());

        postRequestMap.put(UriPattern.REGISTRATION, new RegisterPostCommand());
        postRequestMap.put(UriPattern.LOGIN, new LoginPostCommand());
        postRequestMap.put(UriPattern.LOGOUT, new LogoutCommand());
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
