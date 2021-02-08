package by.daryazalevskaya.finalproject.dao.pool;

import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;


@Log4j2
public final class ConnectionPool {

    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int connectionTimeout;

    private ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<ProxyConnection> freeConnections = new LinkedBlockingQueue<>();
    private List<ProxyConnection> usedConnections = new LinkedList<>();

    private ConnectionPool() {
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }


    public Connection getConnection() throws PoolException {
        lock.lock();

        ProxyConnection connection = null;
        while (connection == null) {

            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(connectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                            log.error("Can't connect to db");
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {

                    throw new PoolException("The limit of database connections is exceeded");
                }
            } catch (InterruptedException ex) {
                log.error(ex);
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                throw new PoolException("Can't connect to db");
            }
        }
        usedConnections.add(connection);
        log.info(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection",
                usedConnections.size(), freeConnections.size()));
        lock.unlock();
        return connection;
    }

    public void init(String driverClass, String url, String user, String password, int startSize, int maxSize, int connectionTimeout) throws PoolException {
        lock.lock();
        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.connectionTimeout = connectionTimeout;

            for (int counter = 0; counter < startSize; ++counter) {
                freeConnections.put(createConnection());
            }
        } catch (InterruptedException ex) {
            log.error(ex);
            Thread.currentThread().interrupt();
        } catch (ClassNotFoundException | SQLException e) {
            throw new PoolException("It is impossible to initialize connection pool");
        } finally {
            lock.unlock();
        }
    }


    private ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(url, user, password));
    }

    void freeConnection(ProxyConnection connection) {
        lock.lock();
        try {
            if (connection.isValid(connectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                log.info(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection",
                        usedConnections.size(), freeConnections.size()));
            }
        } catch (InterruptedException ex) {
            log.error(ex);
            Thread.currentThread().interrupt();
        } catch (SQLException e1) {
            log.error("Can't free connection", e1.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                log.error(e);
            }
        } finally {
            lock.unlock();
        }
    }


    public void destroy() {
        lock.lock();
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (ProxyConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        usedConnections.clear();
        lock.unlock();
    }


}
