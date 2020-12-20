package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator {

    public User createUser(ResultSet resultSet) throws SQLException {
       return  User.builder()
                .id(resultSet.getInt("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role"))).build();
    }
}
