package by.daryazalevskaya.finalproject.controller;

import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ResourceBundle;

@Log4j2
@WebServlet(urlPatterns = "/final")
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
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }
}
