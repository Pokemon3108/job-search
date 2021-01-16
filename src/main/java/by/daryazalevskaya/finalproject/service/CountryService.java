package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;

public abstract class CountryService extends BaseService<Country> {
    public abstract Integer findIdByCountry(String country) throws DaoException;
}
