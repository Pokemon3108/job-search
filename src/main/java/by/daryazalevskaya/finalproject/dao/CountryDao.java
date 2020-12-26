package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;

public interface CountryDao extends Dao<Country> {
    Integer findIdByCountry(String country) throws DaoException;

}
