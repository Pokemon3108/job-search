package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;

import java.util.List;
import java.util.Optional;

public interface EmployeeLanguageDao extends Dao<EmployeeLanguage> {
    List<Language> readAllLanguages() throws DaoException;

    Optional<Language> readLanguageFromCatalog(Integer id) throws DaoException;
}
