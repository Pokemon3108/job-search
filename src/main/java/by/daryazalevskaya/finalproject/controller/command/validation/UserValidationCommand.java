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


        if (!validator.isValidEmail(user.getEmail())) {
            isValid=false;
            request.setAttribute("invalidEmail", true);
        }
        if (!validator.isValidPassword(user.getPassword())) {
            isValid=false;
            request.setAttribute("invalidPassword", true);
        }
        if (!request.getParameter("confirm-password").equals(user.getPassword())) {
            isValid=false;
            request.setAttribute("differentPassword", true);
        }

        if (!isValid) {
            log.info("Invalid contact format");
            isValid = false;
        }
        return isValid;
    }
}
