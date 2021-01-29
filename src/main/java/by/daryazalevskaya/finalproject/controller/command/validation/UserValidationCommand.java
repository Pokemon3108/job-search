package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;
import by.daryazalevskaya.finalproject.service.validator.UserValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class UserValidationCommand implements ValidationCommand {

    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid = true;
        UserBuilder builder = new UserBuilder();
        User user = builder.build(request);
        UserValidator validator = new UserValidator();

        int errors = 0;
        if (!validator.isValidEmail(user.getEmail())) {
            ++errors;
            request.setAttribute("invalidEmail", true);
        }
        if (!validator.isValidPassword(user.getPassword())) {
            ++errors;
            request.setAttribute("invalidPassword", true);
        }

        if (errors != 0) {
            log.info("Invalid contact format");
            isValid = false;
        }
        return isValid;
    }
}
