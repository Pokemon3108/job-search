package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.get.RegisterGetCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.UserValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserAccountComplicatedService;
import by.daryazalevskaya.finalproject.service.UserRoleService;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RegisterPostCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            UserService service = (UserService) serviceFactory.createService(DaoType.USER);

            UserBuilder builder = new UserBuilder();
            User user = builder.build(request);
            ValidationCommand validationCommand = new UserValidationCommand();

            if (!validationCommand.isValid(request, response)) {
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", user.getRole());
                ActionCommand actionCommand = new RegisterGetCommand();
                actionCommand.execute(request, response);

            } else {
                UserAccountComplicatedService userAccountService=
                        (UserAccountComplicatedService) serviceFactory.createService(DaoType.USER_ACCOUNT);

                if (!userAccountService.isRegistered(user)) {
                    request.setAttribute("repeatedEmail", true);
                    request.setAttribute("email", user.getEmail());
                    ActionCommand actionCommand = new RegisterGetCommand();
                    actionCommand.execute(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + UriPattern.LOGIN.getUrl());
                }
            }

        } catch (DaoException | InsertIdDataBaseException e) {
            log.error(e);
            request.setAttribute("error", e);
            request.getServletContext().getRequestDispatcher(PagePath.ERROR505).forward(request, response);
        } finally {
            serviceFactory.close();
        }
    }

}

