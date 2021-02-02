package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;

/**
 * The interface Country dao defines operations with country table
 */
public interface CountryDao extends Dao<Country> {
    /**
     * Read country id by its name
     *
     * @param country the country name that will be looked for
     * @return the id of country
     * @throws DaoException is thrown when occured error with access to database
     */
    Integer readIdByCountry(String country) throws DaoException;
}
