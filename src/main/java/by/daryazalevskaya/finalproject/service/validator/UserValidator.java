package by.daryazalevskaya.finalproject.service.validator;

import by.daryazalevskaya.finalproject.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String PASSWORD_REGEX = "[\\w~!-]{5,}";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public Map<String, String> getInvalidMessages(User user) {
        Map<String, String> messages = new HashMap<>();
        if (!isValidEmail(user.getUsername())) {
            messages.put("email-error", "Invalid email format");
        }
        if (!isPasswordValid(user.getPassword())) {
            messages.put("password-error", "Invalid password format. It should contains more than 5 symbols.");
        }
        return messages;
    }
}
