package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator implements Creator<User> {

    @Override
    public User createEntity(ResultSet set) throws SQLException {
        return  User.builder()
                .id(set.getInt("id"))
                .username(set.getString("username"))
                .password(set.getString("password"))
                .role(Role.valueOf(set.getString("role"))).build();
    }
}
