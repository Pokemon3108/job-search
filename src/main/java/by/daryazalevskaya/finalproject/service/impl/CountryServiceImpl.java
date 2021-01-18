package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.service.CountryService;

import java.util.List;
import java.util.Optional;

public class CountryServiceImpl extends CountryService {
    @Override
    public Integer addNewEntity(Country entity) throws DaoException, InsertIdDataBaseException {
        throw new IllegalOperationException();
    }

    @Override
    public Optional<Country> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        CountryDao countryDao=transaction.createDao(DaoType.COUNTRY);
        return countryDao.read(id);
    }

    @Override
    public void update(Country entity) throws DaoException, PoolException, InsertIdDataBaseException {
        throw new IllegalOperationException();
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {
        throw new IllegalOperationException();
    }

    @Override
    public List<Country> findAll() throws DaoException, PoolException {
        CountryDao countryDao=transaction.createDao(DaoType.COUNTRY);
        return countryDao.findAll();
    }

    @Override
    public Integer findIdByCountry(String country) throws DaoException {
        CountryDao countryDao=transaction.createDao(DaoType.COUNTRY);
        return countryDao.findIdByCountry(country);
    }
}
