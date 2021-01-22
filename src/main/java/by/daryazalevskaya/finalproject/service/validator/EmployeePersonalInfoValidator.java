package by.daryazalevskaya.finalproject.service.validator;

import java.time.LocalDate;

public class EmployeePersonalInfoValidator extends Validator {
    private static final String NAME_PATTERN = "^[a-zа-я\\s\\-]+$";
    private static final int NAME_LENGTH = 30;
    private static final int SURNAME_LENGTH = 50;
    private static final int CITY_LENGTH = 100;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";

    private boolean isValidName(String name, int length) {
        return (isValid(NAME_PATTERN, name.toLowerCase()) && name.length()<length);
    }

    public boolean isValidSurname(String surname) {
        return isValidName(surname, SURNAME_LENGTH);
    }

    public boolean isValidCity(String city) {
        return (super.isValid(CITY_REGEX, city) && city.length() < CITY_LENGTH);
    }

    public boolean isValidFirstName(String name) {
        return isValidName(name, NAME_LENGTH);
    }

    public boolean isValidBirthday(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        return today.isAfter(birthday);
    }

}
