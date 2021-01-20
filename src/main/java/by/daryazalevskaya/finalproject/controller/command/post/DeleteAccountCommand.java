package by.daryazalevskaya.finalproject.controller.command.post;

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
import by.daryazalevskaya.finalproject.service.UserRoleCommonActionsService;
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
        Integer userId = (Integer) request.getSession().getAttribute("user");
        Role role = (Role) request.getSession().getAttribute("role");

        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        UserService userService = new UserServiceImpl();
        userService.setTransaction(transaction);

        try {
            UserRoleCommonActionsService service = new UserRoleCommonActionsService(transaction);
            service.deleteAccount(userId, role);
            userService.delete(userId);

            ActionCommand logoutCommand = new LogoutCommand();
            logoutCommand.execute(request, response);
            transaction.commit();
        } catch (PoolException | DaoException e) {
            log.error(e);
            transaction.rollback();
        } finally {
            transaction.close();
        }
    }
}
