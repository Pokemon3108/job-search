package by.daryazalevskaya.finalproject.controller.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Log4j2
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("User with id " + se.getSession().getAttribute("user")+ " logged out");
    }
}
