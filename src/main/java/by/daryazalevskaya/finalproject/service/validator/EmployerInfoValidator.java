package by.daryazalevskaya.finalproject.service.validator;

/**
 * Class {@code EmployerInfoValidator} is a service for validation {@code Employee} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class EmployerInfoValidator extends Validator {
    private static final int MIN_COMPANY_NAME_LENGTH=3;
    private static final int MAX_COMPANY_NAME_LENGTH = 50;
    private static final int CITY_LENGTH = 100;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";
    private static final String NOT_TAG_REGEX = "[^<>*]+$";
    /**
     *
     * @param name string of company name
     * @return true if validation is successful, else - false
     */
    public boolean isValidCompanyName(String name) {
        return (name.length()>MIN_COMPANY_NAME_LENGTH
                && name.length()<=MAX_COMPANY_NAME_LENGTH
                && super.isValid(NOT_TAG_REGEX, name));
    }

    /**
     *
     * @param city
     * @return true if validation is successful, else - false
     */
    public boolean isValidCity(String city) {
        if (city.length()==0) {
            return true;
        }
        return city.length()<=CITY_LENGTH && isValid(CITY_REGEX, city);
    }
}
