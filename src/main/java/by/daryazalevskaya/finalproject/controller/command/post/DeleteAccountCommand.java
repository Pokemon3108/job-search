package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.UserWithRoleActions;
import by.daryazalevskaya.finalproject.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class DeleteAccountCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session=request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId= (Integer) request.getSession().getAttribute("user");
            Role role= Role.valueOf((String) request.getSession().getAttribute("role"));

            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            UserService userService = new UserServiceImpl();
            userService.setTransaction(transaction);

            try {
                UserWithRoleActions actions = new UserWithRoleActions();
                actions.deleteUser(userId, role, transaction);
            } catch (PoolException | DaoException e) {
                log.error(e);
            }

            request.getSession().invalidate();
        }
        response.sendRedirect(request.getContextPath()+ UriPattern.LOGIN.getUrl());
    }
}
