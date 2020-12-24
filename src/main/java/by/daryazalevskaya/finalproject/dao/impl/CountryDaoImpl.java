package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CountryDaoImpl extends BaseDao implements CountryDao {

    private static final String FIND_COUNTRY_BY_ID_QUERY = "SELECT * FROM country WHERE id =?";
    private static final String FIND_ID_BY_COUNTRY_NAME_QUERY = "SELECT id FROM country WHERE name =?";

    @Override
    public Integer findIdByCountry(String country) throws DaoException {
        final String fieldName="id";
        return findIdByField(country, FIND_ID_BY_COUNTRY_NAME_QUERY, fieldName);
    }

    @Override
    public String findCountryById(int id) throws DaoException {
        final String fieldName="name";
        return findStringFieldById(id, FIND_COUNTRY_BY_ID_QUERY, fieldName);
    }
}
