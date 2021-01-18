package by.daryazalevskaya.finalproject.controller;

import lombok.Getter;

@Getter
public enum UriPattern {
    LOGIN ("/job/login"),
    REGISTRATION("/job/registration"),
    CHANGE_LOCALE("/job/changeLocale"),
    EMPLOYEE_HOME("/job/employee/resume"),
    EMPLOYER_HOME("/job/employer/home"),
    LOGOUT("/job/logout"),
    DELETE_ACCOUNT("/job/delete"),
    CHANGE_EMPLOYEE_CONTACT("/job/employee/changeContact"),
    CHANGE_EMPLOYEE_INFO("/job/employee/changePersonalInfo"),
    CHANGE_EMPLOYEE_SKILLS("/job/employee/changeSkills"),
    CHANGE_JOB_PREFERENCE("/job/employee/changeJobPreference"),
    CHANGE_EMPLOYER_INFO("/job/employer/changeInfo"),
    CHANGE_LANGUAGE("/job/employee/changeLanguage");

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
