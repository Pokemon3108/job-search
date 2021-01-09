package by.daryazalevskaya.finalproject.controller;

import lombok.Getter;

@Getter
public enum UriPattern {
    LOGIN ("/login"),
    REGISTRATION("/registration");


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
