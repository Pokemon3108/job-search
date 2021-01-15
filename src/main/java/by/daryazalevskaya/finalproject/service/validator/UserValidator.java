package by.daryazalevskaya.finalproject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends Validator {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{8,}";
    private static final String EMAIL_PATTERN =
         "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";


    public boolean isPasswordValid(String password) {
//        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();

       return password.length()>8;
    }

    public boolean isValidEmail(String email) {
        return isValid(EMAIL_PATTERN, email);
    }


}
