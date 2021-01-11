package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployerService;

import java.util.List;
import java.util.Optional;

public class EmployerServiceImpl extends EmployerService {

    @Override
    public boolean addNewEntity(Employer entity) throws DaoException, InsertIdDataBaseException {
        boolean isAdded = false;
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        if (employerDao.create(entity) != null) {
            isAdded=true;
        }
        return isAdded;
    }

    @Override
    public Optional<Employer> read(int id) throws DaoException, PoolException {
        return Optional.empty();
    }

    @Override
    public void update(Employer entity) throws DaoException, PoolException {

    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<Employer> findAll() throws DaoException, PoolException {
        return null;
    }
}
