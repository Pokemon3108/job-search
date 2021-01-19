package by.daryazalevskaya.finalproject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    protected boolean isValid(String regex, String statement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(statement);
        return matcher.matches();
    }
}
