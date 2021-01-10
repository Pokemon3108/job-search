package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
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

        UserService service = new UserServiceImpl();
        service.setTransaction(transaction);

        UserBuilder builder = new UserBuilder();
        User user = builder.build(request);
        UserValidator validator = new UserValidator();

        final String page = request.getParameter("page");

        Map<String, String> messages = validator.getInvalidMessages(user);
        if (!messages.isEmpty()) {
            messages.forEach(request::setAttribute);
            request.getServletContext()
                    .getRequestDispatcher(page)
                    .forward(request, response);
        } else {
            try {
                if (!service.addNewEntity(user)) {
                    request.setAttribute("repeatedEmail", "User with this email exists.");
                    request.getServletContext()
                            .getRequestDispatcher(page)
                            .forward(request, response);
                }

                response.sendRedirect(UriPattern.LOGIN.getUrl());
                transaction.commit();
                transaction.close();

            } catch (DaoException | InsertIdDataBaseException e) {
                transaction.rollback();
                transaction.close();
                log.error(e);
                request.setAttribute("error", e);
                request.getServletContext().getRequestDispatcher(ERROR).forward(request, response);
            }
        }
        
    }

}
