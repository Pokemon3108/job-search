package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployeeLanguageDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;

import java.util.List;
import java.util.Optional;

public class EmployeeLanguageServiceImpl extends EmployeeLanguageService {
    @Override
    public Integer addNewEntity(EmployeeLanguage entity) throws DaoException, InsertIdDataBaseException {
        EmployeeLanguageDao dao=transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
        return dao.create(entity);
    }

    @Override
    public Optional<EmployeeLanguage> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        EmployeeLanguageDao dao=transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
        return dao.read(id);
    }

    @Override
    public void update(EmployeeLanguage entity) throws DaoException, PoolException, InsertIdDataBaseException {
        EmployeeLanguageDao dao=transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
         dao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<EmployeeLanguage> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public List<Language> findAllLanguages() throws DaoException {
        EmployeeLanguageDao dao=transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
        return dao.findAllLanguages();
    }
}
