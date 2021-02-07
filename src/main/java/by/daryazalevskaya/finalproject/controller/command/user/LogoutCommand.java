package by.daryazalevskaya.finalproject.controller.command.user;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + UriPattern.LOGIN.getUrl());
    }
}
