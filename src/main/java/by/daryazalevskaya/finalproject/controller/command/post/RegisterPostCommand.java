package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.User;
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

    private static final String ERROR="/view/error500.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl();
        UserBuilder builder = new UserBuilder();
        User user = builder.build(request);
        UserValidator validator = new UserValidator();

        Map<String, String> messages = validator.getInvalidMessages(user);
        if (!messages.isEmpty()) {
            messages.forEach(request::setAttribute);
            request.getServletContext().getRequestDispatcher(request.getContextPath()).forward(request, response);
        } else {
            try {
                service.addNewEntity(user);
                response.sendRedirect(UriPattern.LOGIN.getUrl());
            } catch (DaoException | InsertIdDataBaseException e) {
                log.error(e);
                request.setAttribute("error", e);
                request.getServletContext().getRequestDispatcher(ERROR).forward(request, response);
            }
        }

    }


}
