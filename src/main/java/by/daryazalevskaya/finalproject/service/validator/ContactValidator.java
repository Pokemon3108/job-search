package by.daryazalevskaya.finalproject.service.validator;

/**
 * Class {@code ContactValidator} is a service for validation {@code Contact} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class ContactValidator extends Validator {
    private static final String PHONE_REGEX = "\\+\\d{11,13}";
    private static final String EMAIL_REGEX =
            "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final int EMAIL_LENGTH = 255;
    private static final int PHONE_LENGTH = 20;
    private static final int SKYPE_LENGTH=50;
    private static final String SKYPE_REGEX="[._\\d\\w-]+";

    /**
     *
     * @param phone string of phone number
     * @return true if validation is successful, else - false
     */
    public boolean isValidPhone(String phone) {
        return (isValid(PHONE_REGEX, phone) && phone.length()<=PHONE_LENGTH);
    }

    /**
     *
     * @param email string of email
     * @return true if validation is successful, else - false
     */
    public boolean isValidEmail(String email) {
       return (isValid(EMAIL_REGEX, email) && email.length()<=EMAIL_LENGTH);
    }

    /**
     *
     * @param skype string of skype
     * @return true if validation is successful, else - false
     */
    public boolean isValidSkype(String skype) {
        return (isValid(SKYPE_REGEX, skype) && skype.length()<=SKYPE_LENGTH);
    }


}
