package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.service.dbcreator.Creator;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public abstract class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected <T extends Entity> List<T> findAll(final String query, Creator<T> creator) throws DaoException {
        ResultSet resultSet = null;

        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (creator == null) {
                    throw new DaoException("Can't build object from database");
                }
                entities.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    protected <T extends Entity> Optional<T> readById(int id, final String query, Creator<T> creator) throws DaoException {
        ResultSet resultSet = null;
        T entity = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (creator == null) {
                    throw new DaoException("Can't build object from database");
                }
                entity = creator.createEntity(resultSet);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return Optional.ofNullable(entity);
    }

    protected <T extends Entity> Integer create(T entity, final String query, StatementFormer<T> former) throws InsertIdDataBaseException, DaoException {
        ResultSet resultSet = null;
        Integer id = null;

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            former.fillStatement(statement, entity);
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
            closeSet(resultSet);
        }
        return id;
    }


    protected void delete(int id, final String query) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected String findStringFieldById(int id, final String query, final String fieldName) throws DaoException {
        ResultSet resultSet = null;
        String field = "";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                field = resultSet.getString(fieldName);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return field;
    }

    protected Integer findIdByField(String field, final String query, final String fieldName) throws DaoException {
        ResultSet resultSet = null;
        Integer id = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, field);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(fieldName);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return id;

    }

    protected void closeSet(ResultSet set) {
        try {
            if (Objects.nonNull(set)) {
                set.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    protected <T extends Entity> List<T> findInRange(final String query, Creator<T> creator, int start, int end) throws DaoException {
        ResultSet resultSet = null;

        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, start);
            statement.setInt(2, end);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (creator == null) {
                    throw new DaoException("Can't build object from database");
                }
                entities.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    protected int count(final String query) throws DaoException {
        ResultSet resultSet = null;
        int amount=0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            amount = metaData.getColumnCount();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }
        return amount;
    }
}
