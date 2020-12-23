package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.creator.ContactCreator;
import by.daryazalevskaya.finalproject.service.creator.Creator;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ContactDaoImpl extends ConnectionDao implements ContactDao, DeleteDao  {

    private static final String READ_ALL_QUERY = "SELECT * FROM contact";
    private static final String UPDATE_QUERY = "UPDATE contact SET  telephone = ?, email=?, skype=? WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM contact WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO contact (telephone, email, skype) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM contact WHERE id =?";

    @Override
    public Integer create(Contact entity) throws InsertIdDataBaseException, DaoException {
        ResultSet resultSet = null;
        Integer id;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getTelephone());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getSkype());
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
    public Optional<Contact> read(int id) throws DaoException {
        ResultSet resultSet = null;
        Contact contact=null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_ID_QUERY)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Creator<Contact> creator = new ContactCreator();
                contact = creator.createEntity(resultSet);
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

        return Optional.ofNullable(contact);
    }

    @Override
    public void update(Contact entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getTelephone());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getSkype());
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
    public List<Contact> findAll() throws DaoException {
        ResultSet resultSet = null;

        List<Contact> contacts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_QUERY)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Creator<Contact> creator = new ContactCreator();
                contacts.add(creator.createEntity(resultSet));
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

        return contacts;
    }
}
