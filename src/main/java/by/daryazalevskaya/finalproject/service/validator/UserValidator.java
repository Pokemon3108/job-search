package by.daryazalevskaya.finalproject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{8,}";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public boolean isPasswordValid(String password) {
//        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
//        Matcher matcher = pattern.matcher(password);
//        return matcher.matches();

       return password.length()>8;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
