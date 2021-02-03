package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployeeLanguageDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.service.dbcreator.EmployeeLanguageCreator;
import by.daryazalevskaya.finalproject.service.dbcreator.LanguageCreator;
import by.daryazalevskaya.finalproject.service.statements.LanguageStatementFormer;
import by.daryazalevskaya.finalproject.service.statements.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * The type Employee language dao for access to employee and languages in resume tables
 */
public class EmployeeLanguageDaoImpl extends BaseDao implements EmployeeLanguageDao {

    private static final String READ_ALL_RESUME_LANGUAGES_QUERY = "SELECT level, language_id FROM resume_languages";
    private static final String UPDATE_QUERY = "UPDATE resume_languages SET  language_id = ?, level=?::lang_level WHERE id=?";
    private static final String READ_BY_ID_QUERY = "SELECT language_id, level, id FROM resume_languages WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO resume_languages (language_id, level) VALUES (?, ?::lang_level)";
    private static final String DELETE_QUERY = "DELETE FROM resume_languages WHERE id =?";

    private static final String FIND_ALL_LANGUAGES="SELECT id, name FROM language";

    private static final String FIND_LANGUAGE_FROM_LANGUAGE_CATALOG_BY_ID="SELECT name, id FROM language WHERE id=?";

    @Override
    public Integer create(EmployeeLanguage entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new LanguageStatementFormer());
    }

    @Override
    public Optional<EmployeeLanguage> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new EmployeeLanguageCreator());
    }

    @Override
    public void update(EmployeeLanguage entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<EmployeeLanguage> statementFormer = new LanguageStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(3, entity.getId());
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
    public List<EmployeeLanguage> findAll() throws DaoException {
        return super.findAll(READ_ALL_RESUME_LANGUAGES_QUERY, new EmployeeLanguageCreator());
    }

    @Override
    public List<Language> readAllLanguages() throws DaoException {
        return super.findAll(FIND_ALL_LANGUAGES, new LanguageCreator());
    }

    @Override
    public Optional<Language> readLanguageFromCatalog(Integer id) throws DaoException {
        return super.readById(id, FIND_LANGUAGE_FROM_LANGUAGE_CATALOG_BY_ID,  new LanguageCreator());
    }
}
