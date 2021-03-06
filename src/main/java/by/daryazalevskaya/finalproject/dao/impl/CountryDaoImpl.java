package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.service.dbcreator.CountryCreator;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

/**
 * The type Country dao is a dao for access to country table
 */
@Log4j2
public class CountryDaoImpl extends BaseDao implements CountryDao {

    private static final String FIND_COUNTRY_BY_ID_QUERY = "SELECT name FROM country WHERE id =?";
    private static final String FIND_ID_BY_COUNTRY_NAME_QUERY = "SELECT id FROM country WHERE name =?";
    private static final String READ_ALL_QUERY = "SELECT id,name FROM country";

    @Override
    public Integer readIdByCountry(String country) throws DaoException {
        final String fieldName = "id";
        return findIdByField(country, FIND_ID_BY_COUNTRY_NAME_QUERY, fieldName);
    }

    @Override
    public Integer create(Country entity)  {
        throw new IllegalOperationException("Can't create entity in table 'country'");
    }

    @Override
    public Optional<Country> read(Integer id) throws DaoException {
        final String fieldName = "name";
        String countryName = findStringFieldById(id, FIND_COUNTRY_BY_ID_QUERY, fieldName);
        if (countryName.isEmpty()) {
            return Optional.empty();
        }
        Country country = new Country(id, countryName);
        return Optional.of(country);
    }

    @Override
    public void update(Country entity)  {
        throw new IllegalOperationException("Can't update table 'country'");
    }

    @Override
    public void delete(Integer id)  {
        throw new IllegalOperationException("Can't delete entity from table 'country'");
    }

    @Override
    public List<Country> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new CountryCreator());
    }
}
