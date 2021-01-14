package by.daryazalevskaya.finalproject.controller;

import lombok.Getter;

@Getter
public enum UriPattern {
    LOGIN ("/job/login"),
    REGISTRATION("/job/registration"),
    CHANGE_LOCALE("/job/changeLocale"),
    EMPLOYEE_HOME("/job/employee/resume"),
    EMPLOYER_HOME("/job/employer/home"),
    LOGOUT("/job/logout");

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
