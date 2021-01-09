package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.dbcreator.ContactCreator;
import by.daryazalevskaya.finalproject.service.sql.ContactStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ContactDaoImpl extends BaseDao implements ContactDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM contact";
    private static final String UPDATE_QUERY = "UPDATE contact SET  telephone = ?, email=?, skype=? WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM contact WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO contact (telephone, email, skype) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM contact WHERE id =?";

    @Override
    public Integer create(Contact entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new ContactStatementFormer());
    }

    @Override
    public Optional<Contact> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new ContactCreator());
    }

    @Override
    public void update(Contact entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Contact> statementFormer=new ContactStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        delete(id, DELETE_QUERY);
    }

    @Override
    public List<Contact> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new ContactCreator());
    }
}
