package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public  class UserStatementFormer implements StatementFormer<User> {

    @Override
    public void fillStatement(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getRole().toString());
    }
}