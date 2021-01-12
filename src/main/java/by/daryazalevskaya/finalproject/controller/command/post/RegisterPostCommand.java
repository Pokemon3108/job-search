package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.UserValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UrlSlicer;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.factory.UserWithRoleFactory;
import by.daryazalevskaya.finalproject.service.impl.UserServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.UserBuilder;
import by.daryazalevskaya.finalproject.service.validator.UserValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Log4j2
public class RegisterPostCommand implements ActionCommand {

    private static final String ERROR = "/view/error500.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {

        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();

        try {
            UserService service = new UserServiceImpl();
            service.setTransaction(transaction);

            UserBuilder builder = new UserBuilder();
            User user = builder.build(request);
            UserValidationCommand validationCommand = new UserValidationCommand();
            validationCommand.execute(request, response);

            try {
                if (!service.addNewEntity(user)) {
                    final String page = request.getParameter("page");
                    request.setAttribute("repeatedEmail", true);
                    request.getServletContext()
                            .getRequestDispatcher(page)
                            .forward(request, response);
                }

                UserWithRoleFactory factoryUser = new UserWithRoleFactory();
                factoryUser.createUser(user, transaction);

                response.sendRedirect(UriPattern.LOGIN.getUrl());
                transaction.commit();

            } catch (DaoException | InsertIdDataBaseException e) {
                transaction.rollback();
                log.error(e);
                request.setAttribute("error", e);
                request.getServletContext().getRequestDispatcher(ERROR).forward(request, response);
            }
        } finally {
             transaction.close();
        }

    }

}
