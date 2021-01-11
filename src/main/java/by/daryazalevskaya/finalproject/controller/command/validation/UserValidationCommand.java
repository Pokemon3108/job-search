package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;
import by.daryazalevskaya.finalproject.service.validator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserValidationCommand implements ActionCommand {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBuilder builder = new UserBuilder();
        User user = builder.build(request);
        UserValidator validator = new UserValidator();

        final String page = request.getParameter("page");

        int errors=0;
        if (!validator.isValidEmail(user.getEmail())) {
            ++errors;
            request.setAttribute("invalidEmail", "Invalid email format.");
        }
        if (!validator.isPasswordValid(user.getPassword())) {
            ++errors;
            request.setAttribute("invalidPassword", "Invalid password format. " +
                    "It should contains 1 number, 1 capital, 1 lowercase letter and at least 8 symbols.");
        }

        if (errors!=0) {
            request.getServletContext().getRequestDispatcher(page).forward(request, response);
        }
    }
}
