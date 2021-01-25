package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.UserRoleService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class DeleteAccountCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        Role role = (Role) request.getSession().getAttribute("role");
        try {
            UserRoleService roleService=serviceFactory.createService(role);
            roleService.deleteUser(userId);
            ActionCommand logoutCommand = new LogoutCommand();
            logoutCommand.execute(request, response);
        } catch (DaoException | PoolException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
