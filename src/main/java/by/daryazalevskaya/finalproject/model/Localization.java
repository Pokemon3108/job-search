package by.daryazalevskaya.finalproject.model;

import lombok.Getter;

@Getter
public enum Localization {
    ENGLISH("en_US", "english"),
    RUSSIAN("ru_RU", "russian");

    Localization(String lan, String viewName) {
        this.language = lan;
        this.viewName=viewName;
    }

    private String language;
    private String viewName;

}

