package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.UserDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.creator.Creator;
import by.daryazalevskaya.finalproject.service.creator.UserCreator;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import by.daryazalevskaya.finalproject.service.sql.UserStatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserDaoImpl extends ConnectionDao implements UserDao, DefaultOperationsDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM usr";
    private static final String READ_LOGIN_QUERY = "SELECT * FROM usr WHERE username = ?";
    private static final String UPDATE_QUERY = "UPDATE usr SET  username = ?, password=?, role=?::user_role WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM usr WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO usr (username, password,role) VALUES (?, ?, ?::user_role)";
    private static final String DELETE_QUERY = "DELETE FROM usr WHERE id =?";

    @Override
    public Optional<User> read(String username) throws DaoException {
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_LOGIN_QUERY)) {

            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Creator<User> creator = new UserCreator();
                user = creator.createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return Optional.ofNullable(user);

    }

    @Override
    public Integer create(User entity) throws InsertIdDataBaseException, DaoException {
        ResultSet resultSet = null;
        Integer id=null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            StatementFormer<User> statementFormer=new UserStatementFormer();
            statementFormer.setStatement(statement, entity);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                throw new InsertIdDataBaseException("There is no auto incremented index after trying to add record into table usr");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }
        return id;
    }

    @Override
    public Optional<User> read(int id) throws DaoException {
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_ID_QUERY)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Creator<User> creator = new UserCreator();
                user = creator.createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return Optional.ofNullable(user);
    }

    @Override
    public void update(User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<User> statementFormer=new UserStatementFormer();
            statementFormer.setStatement(statement, entity);
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        delete(id, connection, DELETE_QUERY);
    }

    @Override
    public List<User> findAll() throws DaoException {
        ResultSet resultSet = null;

        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_QUERY)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Creator<User> creator = new UserCreator();
                users.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return users;
    }
}
