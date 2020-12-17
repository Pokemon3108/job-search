package by.daryazalevskaya.finalproject.dao.pool;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
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

    private BlockingQueue<ConnectionFromPool> freeConnections = new LinkedBlockingQueue<>();
    private List<ConnectionFromPool> usedConnections = new CopyOnWriteArrayList<>();

    private ConnectionPool() {
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }


    public synchronized Connection getConnection() {
        ConnectionFromPool connection = null;
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
                    log.error("The limit of number of database connections is exceeded");
                    // throw new PersistentException();
                    //TODO create my exception and throw it

                }
            } catch (InterruptedException | SQLException e) {
                log.error("Can't connect to db");
                //TODO create my exception and throw it
            }
        }
        usedConnections.add(connection);
        log.info(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        return connection;
    }

    public void init(String driverClass, String url, String user, String password, int startSize, int maxSize, int connectionTimeout) {
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
        } catch (InterruptedException | ClassNotFoundException | SQLException e) {
            log.fatal("It is impossible to initialize connection pool", e);
            //TODO create exception and throw it
            //throw new PersistentException(e);
        } finally {
            lock.unlock();
        }
    }


    private ConnectionFromPool createConnection() throws SQLException {
        return new ConnectionFromPool(DriverManager.getConnection(url, user, password));
    }

    void freeConnection(ConnectionFromPool connection) {
        lock.lock();
        try {
            if (connection.isValid(connectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                log.info(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            }
        } catch (SQLException | InterruptedException e1) {
            log.warn("Can't free connection", e1.getMessage());
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }


    public void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (ConnectionFromPool connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        usedConnections.clear();
    }


}
