package by.daryazalevskaya.finalproject.service.validator;


/**
 * Class {@code VacancyValidator} is a service for validation {@code Vacancy} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class VacancyValidator extends Validator {
    private static final int CITY_LENGTH = 100;
    private static final int POSITION = 255;
    private static final int LONG_TEXT_LENGTH=2000;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";


    /**
     *
     * @param city string of city name
     * @return true if city name is correct, else - false
     */
    public boolean isValidCity(String city) {
        return (super.isValid(CITY_REGEX, city) && city.length() < CITY_LENGTH);
    }

    /**
     *
     * @param position
     * @return true if position name is correct, else - false
     */
    public boolean isValidPosition(String position) {
        return  (position.length()<POSITION && super.isValid(NOT_TAG_REGEX, position));
    }

    /**
     *
     * @param text from textarea fields
     * @return true if text name is correct, else - false
     */
    public boolean isValidLongText(String text) {
        return (super.isValid(NOT_TAG_REGEX, text) && text.length()<LONG_TEXT_LENGTH);
    }
}
