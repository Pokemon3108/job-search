package by.daryazalevskaya.finalproject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@code Validator} is a base service for validation text
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class Validator {
    protected static final String NOT_TAG_REGEX="[^<>*]+$";
    /**
     *
     * @param regex is an expression, that statement matches
     * @param statement is a text, that will be validated
     * @return true if matches, else - not
     */
    protected boolean isValid(String regex, String statement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(statement);
        return matcher.matches();
    }
}
