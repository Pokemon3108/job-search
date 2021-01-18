package by.daryazalevskaya.finalproject.service.validator;

public class EmployerInfoValidator {
    private static final int MIN_COMPANY_NAME_LENGTH=3;
    private static final int MAX_COMPANY_NAME_LENGTH = 50;
    private static final int CITY_LENGTH = 100;

    public boolean isValidCompanyName(String name) {
        return (name.length()>MIN_COMPANY_NAME_LENGTH && name.length()<=MAX_COMPANY_NAME_LENGTH);
    }

    public boolean isValidCity(String city) {
        return city.length()<=CITY_LENGTH;
    }
}
