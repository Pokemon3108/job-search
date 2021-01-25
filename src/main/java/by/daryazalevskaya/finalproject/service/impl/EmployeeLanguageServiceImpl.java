package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployeeLanguageDao;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;

import java.util.*;
import java.util.function.Supplier;

public class EmployeeLanguageServiceImpl extends EmployeeLanguageService {
    @Override
    public Integer createLanguage(EmployeeLanguage language) throws DaoException, TransactionException {
        try {
            EmployeeLanguageDao dao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
            return dao.create(language);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<EmployeeLanguage> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        EmployeeLanguageDao dao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
        Optional<EmployeeLanguage> employeeLanguage= dao.read(id);
        if (employeeLanguage.isPresent()) {
            Optional<Language> language = dao.findLanguageFromCatalog(employeeLanguage.get().getName().getId());
            language.ifPresent(lang->employeeLanguage.get().setName(lang));
        }
        return employeeLanguage;
    }

    @Override
    public void update(EmployeeLanguage entity) throws DaoException, TransactionException {
        try {
            EmployeeLanguageDao dao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
            dao.update(entity);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            EmployeeLanguageDao dao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
            dao.delete(id);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Language> findAllLanguages() throws DaoException {
        EmployeeLanguageDao dao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
        return dao.findAllLanguages();
    }
}
