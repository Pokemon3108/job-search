package by.daryazalevskaya.finalproject.service.validator;

import java.time.LocalDate;

public class PersonalInfoValidator extends Validator {
    private static final String NAME_PATTERN = "^[a-zа-я\\s\\-]+$";

    public boolean isValidName(String name) {
        return isValid(NAME_PATTERN, name.toLowerCase());
    }

    public boolean isValidBirthday(LocalDate birthday) {
        LocalDate today=LocalDate.now();
        return today.isAfter(birthday);
    }

}
