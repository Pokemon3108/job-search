package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.get.RegisterGetCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.UserValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.UserRoleCommonActionsService;
import by.daryazalevskaya.finalproject.service.impl.UserServiceImpl;
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

        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();

        try {
            UserService service = new UserServiceImpl();
            service.setTransaction(transaction);

            UserBuilder builder = new UserBuilder();
            User user = builder.build(request);
            ValidationCommand validationCommand = new UserValidationCommand();

            if (!validationCommand.isValid(request, response)) {
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", user.getRole());
                ActionCommand actionCommand = new RegisterGetCommand();
                actionCommand.execute(request, response);

            } else if (service.addNewEntity(user) == null) {
                final String page = request.getParameter("page");
                request.setAttribute("repeatedEmail", true);
                request.setAttribute("email", user.getEmail());
                request.setAttribute("role", user.getRole());
                request.getServletContext().getRequestDispatcher(page).forward(request, response);

            } else {

                UserRoleCommonActionsService roleService = new UserRoleCommonActionsService(transaction);
                roleService.createAccount(user);

                response.sendRedirect(request.getContextPath() + UriPattern.LOGIN.getUrl());
                transaction.commit();

            }

        } catch (DaoException | InsertIdDataBaseException e) {
            transaction.rollback();
            log.error(e);
            request.setAttribute("error", e);
            request.getServletContext().getRequestDispatcher(PagePath.ERROR505).forward(request, response);
        } finally {
            transaction.close();
        }
    }

}

