package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class CountryDaoImpl extends ConnectionDao implements CountryDao, DefaultOperationsDao {

    private static final String FIND_COUNTRY_BY_ID_QUERY = "SELECT * FROM country WHERE id =?";
    private static final String FIND_ID_BY_COUNTRY_NAME_QUERY = "SELECT id FROM country WHERE name =?";

    @Override
    public Integer findIdByCountry(String country) throws DaoException {
        ResultSet resultSet = null;
        Integer id=null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_ID_BY_COUNTRY_NAME_QUERY)) {
            statement.setString(1, country);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id=resultSet.getInt("name");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return id;
    }

    @Override
    public String findCountryById(int id) throws DaoException {
        final String fieldName="name";
        return findFieldById(id, connection, FIND_COUNTRY_BY_ID_QUERY, fieldName);
    }
}
