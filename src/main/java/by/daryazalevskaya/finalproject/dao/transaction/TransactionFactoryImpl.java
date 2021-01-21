package by.daryazalevskaya.finalproject.dao.transaction;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    public TransactionFactoryImpl() throws ConnectionException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionException("It is impossible to turn off autocommiting for database connection", e);
        } catch (PoolException ex) {
            throw new ConnectionException(ex);
        }
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() throws TransactionException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new TransactionException(e);
        }
    }
}
