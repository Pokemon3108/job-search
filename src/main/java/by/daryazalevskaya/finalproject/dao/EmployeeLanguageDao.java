package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;

import java.util.List;
import java.util.Optional;

/**
 * The interface Employee language dao defines operations with employee and  employee languages
 */
public interface EmployeeLanguageDao extends Dao<EmployeeLanguage> {
    /**
     * Read all languages from catalog of languages
     *
     * @return the list of languages
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Language> readAllLanguages() throws DaoException;

    /**
     * Read language from catalog
     *
     * @param id the id of language to be read
     * @return the optional
     * @throws DaoException the dao exception  is thrown when occures error with access to database
     */
    Optional<Language> readLanguageFromCatalog(Integer id) throws DaoException;
}
