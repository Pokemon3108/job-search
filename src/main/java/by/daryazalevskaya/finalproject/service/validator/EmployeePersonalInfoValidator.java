package by.daryazalevskaya.finalproject.service.validator;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class {@code EmployeePersonalInfoValidator} is a service for validation {@code EmployeePersonalInfo} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class EmployeePersonalInfoValidator extends Validator {
    private static final String NAME_PATTERN = "^[a-zа-я\\s\\-]+$";
    private static final int NAME_LENGTH = 30;
    private static final int SURNAME_LENGTH = 50;
    private static final int CITY_LENGTH = 100;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";
    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 100;

    private boolean isValidName(String name, int length) {
        return (isValid(NAME_PATTERN, name.toLowerCase()) && name.length() < length);
    }

    /**
     * @param surname
     * @return true if validation is successful, else - false
     */
    public boolean isValidSurname(String surname) {
        return isValidName(surname, SURNAME_LENGTH);
    }

    /**
     * @param city
     * @return true if validation is successful, else - false
     */
    public boolean isValidCity(String city) {
        if (city.length() == 0) {
            return true;
        }
        return (super.isValid(CITY_REGEX, city) && city.length() < CITY_LENGTH);
    }

    /**
     * @param name
     * @return true if validation is successful, else - false
     */
    public boolean isValidFirstName(String name) {
        return isValidName(name, NAME_LENGTH);
    }

    /**
     * @param birthday is a date of person's birthday
     * @return true if birthday is in permissible borders
     */
    public boolean isValidBirthday(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthday, today).getYears();
        return (age >= MIN_AGE && age < MAX_AGE);
    }

}
