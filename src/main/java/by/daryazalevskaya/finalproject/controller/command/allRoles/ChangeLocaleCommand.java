package by.daryazalevskaya.finalproject.controller.command.allRoles;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Localization;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String cookieName="lang";

        Localization lang=Localization.valueOf(request.getParameter("lang"));
        Cookie c=new Cookie(cookieName, lang.getLanguage());
        c.setPath("/");
        response.addCookie(c);
    }
}
