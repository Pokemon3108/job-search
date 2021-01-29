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
        getRequestMap.put(UriPattern.START, new LoadMainPageCommand());
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
        getRequestMap.put(UriPattern.CHANGE_EMPLOYER_CONTACT, new EmployerGetContactCommand());
        getRequestMap.put(UriPattern.OPEN_VACANCY, new OpenVacancyGetCommand());
        getRequestMap.put(UriPattern.EDIT_VACANCY, new EditVacancyCommand());
        getRequestMap.put(UriPattern.EMPLOYER_VACANCY_LIST, new ShowEmployerVacanciesCommand());
        getRequestMap.put(UriPattern.SHOW_ALL_VACANCIES, new ShowAllVacanciesCommand());
        getRequestMap.put(UriPattern.VACANCY_FULL_SHOW, new FullVacancyDescriptionGetCommand());
        getRequestMap.put(UriPattern.EMPLOYEE_VACANCIES, new ShowEmployeeVacanciesCommand());
        getRequestMap.put(UriPattern.FILTER_VACANCIES, new FilterVacanciesCommand());
        getRequestMap.put(UriPattern.SHOW_RESUME, new ShowResumeCommand());
        getRequestMap.put(UriPattern.EMPLOYEE_LIST, new ShowAllResumesCommand());

        postRequestMap.put(UriPattern.REGISTRATION, new RegisterPostCommand());
        postRequestMap.put(UriPattern.LOGIN, new LoginPostCommand());
        postRequestMap.put(UriPattern.LOGOUT, new LogoutCommand());
        postRequestMap.put(UriPattern.DELETE_ACCOUNT, new DeleteAccountCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_CONTACT, new SaveContactEmployeeCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_INFO, new SaveEmployeePersonalInfoCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYEE_SKILLS, new SaveSkillsCommand());
        postRequestMap.put(UriPattern.CHANGE_JOB_PREFERENCE, new SaveJobPreferenceCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYER_INFO, new SaveEmployerInfoCommand());
        postRequestMap.put(UriPattern.CHANGE_LANGUAGE, new SaveEmployeeLanguageCommand());
        postRequestMap.put(UriPattern.CHANGE_EMPLOYER_CONTACT, new SaveContactEmployerCommand());
        postRequestMap.put(UriPattern.SAVE_VACANCY_CHANGES, new SaveVacancyChangesCommand());
        postRequestMap.put(UriPattern.DELETE_VACANCY, new DeleteVacancyCommand());
        postRequestMap.put(UriPattern.RESPOND_ON_VACANCY, new RespondVacancyCommand());
        postRequestMap.put(UriPattern.DELETE_EMPLOYEE_VACANCY, new DeleteEmployeeVacancy());

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
