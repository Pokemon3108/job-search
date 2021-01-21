package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public abstract class EmployeeLanguageService extends BaseService {
    public abstract List<Language> findAllLanguages() throws DaoException;

    public abstract Integer addNewLanguage(EmployeeLanguage language) throws DaoException, TransactionException;

    public abstract Optional<EmployeeLanguage> read(Integer id) throws DaoException;

    public abstract void update(EmployeeLanguage vacancy) throws DaoException, TransactionException;

    public abstract void delete(int id) throws DaoException, TransactionException;

}
