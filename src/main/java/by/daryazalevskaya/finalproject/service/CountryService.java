package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;

import java.util.List;
import java.util.Optional;

public abstract class CountryService extends BaseService {
    public abstract Integer findIdByCountry(String country) throws DaoException;

    public abstract Optional<Country> read(Integer id) throws DaoException;

    public abstract List<Country> findAll() throws DaoException;
}
