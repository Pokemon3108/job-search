package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.UserDao;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.creator.UserCreatorDB;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserDaoImpl extends ConnectionDao implements UserDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM usr";
    private static final String READ_LOGIN_QUERY = "SELECT * FROM usr WHERE username = ?";
    private static final String UPDATE_QUERY = "UPDATE usr SET  username = ?, password=?, role=?::user_role WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM usr WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO usr (username, role,password) VALUES (?, ?::user_role, ?)";

    @Override
    public User read(String username) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(READ_LOGIN_QUERY);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserCreatorDB creator = new UserCreatorDB();
                user = creator.createUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return user;
    }

    @Override
    public Integer create(User entity) throws InsertIdDataBaseException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer id = null;
        try {
            statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getRole().toString());
            statement.setString(3, entity.getPassword());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                log.error("There is no autoincremented index after trying to add record into table usr");
                throw new InsertIdDataBaseException();
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }
        return id;
    }

    @Override
    public User read(int id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(READ_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserCreatorDB creator = new UserCreatorDB();
                user = creator.createUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return user;
    }

    @Override
    public void update(User entity) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<User> users = new ArrayList<>();
        try {
            statement = connection.prepareStatement(READ_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserCreatorDB creator = new UserCreatorDB();
                users.add(creator.createUser(resultSet));
            }
        } catch (SQLException e) {
            log.error(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return users;
    }
}
