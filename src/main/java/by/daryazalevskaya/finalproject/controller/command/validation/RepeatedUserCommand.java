package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RepeatedUserCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) throws DaoException, TransactionException {
        UserBuilder builder = new UserBuilder();
        User user = builder.build(request);
        ServiceFactory serviceFactory = null;
        try {
            serviceFactory = new ServiceFactoryImpl();
            boolean isValid = true;
            UserService userService = (UserService) serviceFactory.createService(DaoType.USER);
            if (userService.userExists(user.getEmail())) {
                isValid = false;
                request.setAttribute("repeatedEmail", true);
            }
            return isValid;
        } catch (ConnectionException ex) {
            throw new DaoException(ex);
        } finally {
            serviceFactory.close();
        }

    }
}
