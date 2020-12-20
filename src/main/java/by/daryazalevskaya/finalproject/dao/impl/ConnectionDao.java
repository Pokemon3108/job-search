package by.daryazalevskaya.finalproject.dao.impl;

import java.sql.Connection;

abstract class ConnectionDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
