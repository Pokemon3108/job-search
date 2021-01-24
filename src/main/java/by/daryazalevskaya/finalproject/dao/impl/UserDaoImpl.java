package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.UserDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.dbcreator.Creator;
import by.daryazalevskaya.finalproject.service.dbcreator.UserCreator;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import by.daryazalevskaya.finalproject.service.sql.UserStatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserDaoImpl extends BaseDao implements UserDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM usr";
    private static final String READ_LOGIN_QUERY = "SELECT * FROM usr WHERE email = ?";
    private static final String UPDATE_QUERY = "UPDATE usr SET  email = ?, password=?, role=?::user_role WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM usr WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO usr (email, password,role) VALUES (?, ?, ?::user_role)";
    private static final String DELETE_QUERY = "DELETE FROM usr WHERE id =?";

    @Override
    public Optional<User> read(String email) throws DaoException {
        ResultSet resultSet = null;
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_LOGIN_QUERY)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Creator<User> creator = new UserCreator();
                user = creator.createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return Optional.ofNullable(user);

    }

    @Override
    public Integer create(User entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new UserStatementFormer());
    }

    @Override
    public Optional<User> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new UserCreator());
    }

    @Override
    public void update(User entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<User> statementFormer = new UserStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        delete(id, DELETE_QUERY);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new UserCreator());
    }
}
