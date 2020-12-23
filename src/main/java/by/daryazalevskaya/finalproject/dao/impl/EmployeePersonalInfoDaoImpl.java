package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployeePersonalInfoDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;

import java.util.List;
import java.util.Optional;

public class EmployeePersonalInfoDaoImpl extends ConnectionDao implements EmployeePersonalInfoDao, DefaultOperationsDao {

    @Override
    public Integer create(EmployeePersonalInfo entity) throws InsertIdDataBaseException, DaoException {
        return null;
    }

    @Override
    public Optional<EmployeePersonalInfo> read(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void update(EmployeePersonalInfo entity) throws DaoException {

    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public List<EmployeePersonalInfo> findAll() throws DaoException {
        return null;
    }
}
