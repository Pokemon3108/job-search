package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String jsp="/view/authorization/registration.jsp";
        request.getServletContext()
                .getRequestDispatcher(jsp)
                .forward(request, response);
    }
}
