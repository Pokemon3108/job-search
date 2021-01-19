package by.daryazalevskaya.finalproject.service.validator;

public class VacancyValidator extends Validator {
    private static final int CITY_LENGTH = 100;
    private static final int POSITION = 255;
    private static final int LONG_TEXT_LENGTH=2000;
    private static final String CITY_REGEX = "[\\w\\-\\s]";

    public boolean isValidCity(String city) {
        return (super.isValid(CITY_REGEX, city) && city.length() < CITY_LENGTH);
    }

    public boolean isValidPosition(String position) {
        return position.length()<LONG_TEXT_LENGTH;
    }

    public boolean isValidLongText(String text) {
        return text.length()<LONG_TEXT_LENGTH;
    }
}
