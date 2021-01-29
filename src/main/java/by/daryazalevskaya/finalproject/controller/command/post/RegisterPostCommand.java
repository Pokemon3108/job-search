package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.get.RegisterGetCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.RepeatedUserCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.UserValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserAccountComplicatedService;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RegisterPostCommand extends ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserBuilder builder = new UserBuilder();
            User user = builder.build(request);
            ValidationCommand validationCommand = new UserValidationCommand();
            ValidationCommand repeatedUserCommand = new RepeatedUserCommand();
            if (!validationCommand.isValid(request, response) || !repeatedUserCommand.isValid(request, response)) {
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", user.getRole());
                ActionCommand actionCommand = new RegisterGetCommand();
                actionCommand.execute(request, response);
            } else {
                UserAccountComplicatedService userAccountService =
                        (UserAccountComplicatedService) serviceFactory.createService(DaoType.USER_ACCOUNT);
                userAccountService.register(user);
                response.sendRedirect(request.getContextPath() + UriPattern.LOGIN.getUrl());
            }
        } catch (DaoException | InsertIdDataBaseException | TransactionException e) {
            log.error(e);
            request.setAttribute("error", e);
            request.getServletContext().getRequestDispatcher(PagePath.ERROR505).forward(request, response);
        }
    }

}

