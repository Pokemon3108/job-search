package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.LanguageDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.service.creator.LanguageCreator;
import by.daryazalevskaya.finalproject.service.sql.LanguageStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LanguageDaoImpl extends BaseDao implements LanguageDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM language";
    private static final String UPDATE_QUERY = "UPDATE language SET  name = ?, level=? WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM language WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO language (name, level) VALUES (?, ?::lang_level)";
    private static final String DELETE_QUERY = "DELETE FROM language WHERE id =?";

    @Override
    public Integer create(Language entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new LanguageStatementFormer());
    }

    @Override
    public Optional<Language> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new LanguageCreator());
    }

    @Override
    public void update(Language entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Language> statementFormer = new LanguageStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(3, entity.getId());
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
    public List<Language> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new LanguageCreator());
    }
}
