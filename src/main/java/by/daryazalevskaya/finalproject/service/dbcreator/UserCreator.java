package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type UserCreator creator is used for creation {@code User} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.User}
 */
public class UserCreator extends Creator<User> {

    @Override
    public User createEntity(ResultSet set) throws SQLException {
        return  User.builder()
                .id(set.getInt("id"))
                .email(set.getString("email"))
                .password(set.getString("password"))
                .role(Role.valueOf(set.getString("role"))).build();
    }
}
