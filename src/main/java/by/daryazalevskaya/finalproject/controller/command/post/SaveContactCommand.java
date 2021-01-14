package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.UserRoleCommonActionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class SaveContactCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            Role role = (Role) request.getSession().getAttribute("role");

            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();

//            UserRoleCommonActionsService service = new UserRoleCommonActionsService(transaction);
//            service.updateContact(userId, role);


            request.getServletContext()
                    .getRequestDispatcher(PagePath.CONTACT)
                    .forward(request, response);
        }
    }
}
