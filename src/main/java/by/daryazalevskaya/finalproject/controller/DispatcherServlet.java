package by.daryazalevskaya.finalproject.controller;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.CommandManager;
import by.daryazalevskaya.finalproject.controller.command.CommandManagerFactory;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Log4j2
@WebServlet("/controller")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        String driver = resource.getString("db.driver");
        int poolSizeMax = Integer.parseInt(resource.getString("db.poolMaxSize"));
        int startPoolSize = Integer.parseInt(resource.getString("db.poolStartSize"));
        int timeout = Integer.parseInt(resource.getString("db.connectionTimeout"));


        try {
            ConnectionPool.getInstance().init(driver,
                    url, user, pass, startPoolSize, poolSizeMax, timeout);
        } catch (PoolException e) {
            log.error("Can't create application");
            destroy();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ConnectionException | TransactionException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(req.getRequestURL());
        try {
            process(req, resp);
        } catch (ConnectionException | TransactionException e) {
            log.error(e);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ConnectionException, TransactionException {
        CommandManager commandManager = CommandManagerFactory.getManager(getFactory());
        try {
            ActionCommand command = (ActionCommand) req.getAttribute("command");
            if (command != null) {
                commandManager.execute(command, req, resp);
            } else {
                resp.sendError(404);
            }
        } finally {
            commandManager.close();
        }
    }

    public ServiceFactory getFactory() throws ConnectionException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }
}
