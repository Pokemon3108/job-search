package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class StatementFormer<T> {
    public abstract void setStatement(PreparedStatement statement, T entity) throws SQLException;
}