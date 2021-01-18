package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.get.*;
import by.daryazalevskaya.finalproject.controller.command.post.*;

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
        getRequestMap.put(UriPattern.CHANGE_EMPLOYEE_CONTACT, new ContactEmployeeGetCommand());
        getRequestMap.put(UriPattern.CHANGE_EMPLOYEE_INFO, new PersonalInfoGetCommand());
        getRequestMap.put(UriPattern.CHANGE_EMPLOYEE_SKILLS, new EmployeeSkillsGetCommand());
        getRequestMap.put(UriPattern.CHANGE_JOB_PREFERENCE, new JobPreferenceGetCommand());
        getRequestMap.put(UriPattern.CHANGE_EMPLOYER_INFO, new EmployerInfoGetCommand());
        getRequestMap.put(UriPattern.CHANGE_LANGUAGE, new LanguageGetCommand());

        postRequestMap.put(UriPattern.REGISTRATION, new RegisterPostCommand());
        postRequestMap.put(UriPattern.LOGIN, new LoginPostCommand());
        postRequestMap.put(UriPattern.LOGOUT, new LogoutCommand());
        postRequestMap.put(UriPattern.DELETE_ACCOUNT, new DeleteAccountCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_CONTACT, new SaveContactEmployeeCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_INFO, new SavePersonalInfoCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_SKILLS, new SaveSkillsCommand());
        postRequestMap.put(UriPattern.CHANGE_JOB_PREFERENCE, new SaveJobPreferenceCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYER_INFO, new SaveEmployerInfoCommand());
        postRequestMap.put(UriPattern.CHANGE_LANGUAGE, new SaveEmployeeLanguageCommand());
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
