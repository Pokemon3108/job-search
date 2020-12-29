package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.model.Country;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class CountryDaoImpl extends BaseDao implements CountryDao {

    private static final String FIND_COUNTRY_BY_ID_QUERY = "SELECT * FROM country WHERE id =?";
    private static final String FIND_ID_BY_COUNTRY_NAME_QUERY = "SELECT id FROM country WHERE name =?";

    @Override
    public Integer findIdByCountry(String country) throws DaoException {
        final String fieldName = "id";
        return findIdByField(country, FIND_ID_BY_COUNTRY_NAME_QUERY, fieldName);
    }


    @Override
    public Integer create(Country entity) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public Optional<Country> read(int id) throws DaoException {
        final String fieldName = "name";
        String countryName = findStringFieldById(id, FIND_COUNTRY_BY_ID_QUERY, fieldName);
        Country country=new Country(id, countryName);
        return Optional.ofNullable(country);
    }

    @Override
    public void update(Country entity) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public void delete(int id) throws IllegalOperationException {
        throw new IllegalOperationException();
    }

    @Override
    public List<Country> findAll() throws  IllegalOperationException {
        throw new IllegalOperationException();
    }
}