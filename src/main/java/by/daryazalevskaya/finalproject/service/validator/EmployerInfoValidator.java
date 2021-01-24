package by.daryazalevskaya.finalproject.service.validator;

public class EmployerInfoValidator extends Validator {
    private static final int MIN_COMPANY_NAME_LENGTH=3;
    private static final int MAX_COMPANY_NAME_LENGTH = 50;
    private static final int CITY_LENGTH = 100;
    private static final String CITY_REGEX = "^[a-zA-Zа-яА-Я\\s\\/\\-\\)\\(\\`\\.\\\"\\']+$";

    public boolean isValidCompanyName(String name) {
        return (name.length()>MIN_COMPANY_NAME_LENGTH && name.length()<=MAX_COMPANY_NAME_LENGTH);
    }

    public boolean isValidCity(String city) {
        if (city.length()==0) {
            return true;
        }
        return city.length()<=CITY_LENGTH && isValid(CITY_REGEX, city);
    }
}
