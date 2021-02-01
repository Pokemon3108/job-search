package by.daryazalevskaya.finalproject.service.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class StatementFormer<T> {

    public abstract void fillStatement(PreparedStatement statement, T entity) throws SQLException;
}