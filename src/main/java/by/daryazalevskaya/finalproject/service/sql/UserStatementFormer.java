package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public  class UserStatementFormer extends StatementFormer<User> {

    @Override
    public void setStatement(PreparedStatement statement, User entity) throws SQLException {
        statement.setString(1, entity.getUsername());
        statement.setString(2, entity.getPassword());
        statement.setString(3, entity.getRole().toString());
    }
}