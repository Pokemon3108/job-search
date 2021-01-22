package by.daryazalevskaya.finalproject.controller;

import lombok.Getter;

@Getter
public enum UriPattern {
    START("/"),
    LOGIN ("/job/login"),
    REGISTRATION("/job/registration"),
    CHANGE_LOCALE("/job/changeLocale"),
    EMPLOYEE_HOME("/job/employee/resume"),
    EMPLOYER_HOME("/job/employer/home"),
    LOGOUT("/job/auth/logout"),
    DELETE_ACCOUNT("/job/auth/delete"),
    CHANGE_EMPLOYEE_CONTACT("/job/employee/changeContact"),
    CHANGE_EMPLOYEE_INFO("/job/employee/changePersonalInfo"),
    CHANGE_EMPLOYEE_SKILLS("/job/employee/changeSkills"),
    CHANGE_JOB_PREFERENCE("/job/employee/changeJobPreference"),
    CHANGE_EMPLOYER_INFO("/job/employer/changeInfo"),
    CHANGE_LANGUAGE("/job/employee/changeLanguage"),
    CHANGE_EMPLOYER_CONTACT("/job/employer/changeContact"),
    OPEN_VACANCY("/job/employer/openVacancy"),
    EDIT_VACANCY("/job/employer/editVacancy"),
    EMPLOYER_VACANCY_LIST("/job/employer/vacancyList"),
    SAVE_VACANCY_CHANGES("/job/employer/saveVacancyChanges"),
    DELETE_VACANCY("/job/employer/deleteVacancy"),
    SHOW_ALL_VACANCIES("/job/showAllVacancies"),
    VACANCY_FULL_SHOW("/job/vacancy"),
    RESPOND_ON_VACANCY("/job/employee/respondVacancy"),
    SHOW_EMPLOYEE_VACANCIES("/job/employee/myVacancies");

    UriPattern(String url) {
        this.url = url;
    }

    private String url;

    public static UriPattern getEnumByUri(String value) {
        if (value != null) {
            for (UriPattern pt : UriPattern.values()) {
                if (value.equalsIgnoreCase(pt.url)) {
                    return pt;
                }
            }
        }

        return null;
    }

}
