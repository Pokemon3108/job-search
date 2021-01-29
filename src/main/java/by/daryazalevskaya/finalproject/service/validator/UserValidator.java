package by.daryazalevskaya.finalproject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@code UserValidator} is a service for validation {@code User} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class UserValidator extends Validator {
    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}";
    private static final String EMAIL_PATTERN =
         "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    /**
     *
     * @param password string of company name
     * @return true if password correct, else - false
     */
    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     *
     * @param email
     * @return true if email correct, else - false
     */
    public boolean isValidEmail(String email) {
        return isValid(EMAIL_PATTERN, email);
    }


}
