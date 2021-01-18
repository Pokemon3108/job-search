package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployeePersonalInfoDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.service.EmployeePersonalInfoService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeePersonalInfoServiceImpl extends EmployeePersonalInfoService {
    @Override
    public Integer addNewEntity(EmployeePersonalInfo entity) throws DaoException, InsertIdDataBaseException {
        EmployeePersonalInfoDao dao=transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
        return dao.create(entity);
    }

    @Override
    public Optional<EmployeePersonalInfo> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        EmployeePersonalInfoDao dao=transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
        Optional<EmployeePersonalInfo> info= dao.read(id);
        CountryDao countryDao=transaction.createDao(DaoType.COUNTRY);
        if (info.isPresent()) {
            Optional<Country> country=countryDao.read(info.get().getCountry().getId());
            country.ifPresent(c->info.get().setCountry(c));
        }

        return info;
    }

    @Override
    public void update(EmployeePersonalInfo entity) throws DaoException, PoolException, InsertIdDataBaseException {
        EmployeePersonalInfoDao dao=transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
        if (Objects.isNull(entity.getId()) && dao.read(entity.getId()).isEmpty()) {
            dao.create(entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<EmployeePersonalInfo> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public List<Country> getCountries() throws DaoException {
        CountryDao countryDao=transaction.createDao(DaoType.COUNTRY);
        return countryDao.findAll();
    }


}
