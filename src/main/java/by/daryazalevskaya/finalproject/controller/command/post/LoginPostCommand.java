package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.UserValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class LoginPostCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (Objects.nonNull(email) && Objects.nonNull(password)) {
            UserService service = (UserService) serviceFactory.createService(DaoType.USER);
            try {
                final String page = request.getParameter("page");
                if (!service.isValidLoginAndPassword(email, password)) {
                    request.setAttribute("loginError", true);
                    request.getServletContext().getRequestDispatcher(page).forward(request, response);
                } else if (request.getSession(false) == null || request.getSession(false).getAttribute("user") != null) {
                    request.setAttribute("alreadyLogged", true);
                    request.getServletContext().getRequestDispatcher(page).forward(request, response);
                } else {
                    User user = service.findUserByEmail(email).get();
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.getId());
                    session.setAttribute("role", user.getRole());
                    response.sendRedirect(request.getContextPath() + getRedirectPath(user.getRole()));
                }

            } catch (DaoException e) {
                log.error(e);
                response.sendError(500);
            }
        }
    }

    private String getRedirectPath(Role role) {
        String path = "";
        switch (role) {
            case EMPLOYEE:
                path = "/job/employee/resume";
                break;
            case EMPLOYER:
                path = "/job/employer/home";
                break;
        }
        return path;
    }


}
