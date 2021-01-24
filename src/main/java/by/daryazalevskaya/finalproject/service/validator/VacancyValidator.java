package by.daryazalevskaya.finalproject.service.validator;

public class VacancyValidator extends Validator {
    private static final int CITY_LENGTH = 100;
    private static final int POSITION = 255;
    private static final int LONG_TEXT_LENGTH=2000;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";
    private static final String NOT_TAG_REGEX="[^<>*]+$";

    public boolean isValidCity(String city) {
        return (super.isValid(CITY_REGEX, city) && city.length() < CITY_LENGTH);
    }

    public boolean isValidPosition(String position) {
        return  position.length()<POSITION;
    }

    public boolean isValidLongText(String text) {
        return (super.isValid(NOT_TAG_REGEX, text) && text.length()<LONG_TEXT_LENGTH);
    }
}
