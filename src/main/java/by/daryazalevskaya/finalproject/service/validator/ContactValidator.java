package by.daryazalevskaya.finalproject.service.validator;

public class ContactValidator extends Validator {
    private static final String PHONE_REGEX = "\\+\\d{12}";
    private static final String EMAIL_PATTERN =
            "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final int EMAIL_LENGTH = 255;
    private static final int PHONE_LENGTH = 20;


    public boolean isValidPhone(String phone) {
        return (isValid(PHONE_REGEX, phone) && phone.length()<PHONE_LENGTH);
    }

    public boolean isValidEmail(String email) {
       return (isValid(EMAIL_PATTERN, email) && email.length()<PHONE_LENGTH);
    }


}
