package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Country creator is used for creation {@code Country} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.Country}
 */
public class CountryCreator extends Creator<Country> {
    @Override
    public Country createEntity(ResultSet set) throws SQLException {
        return Country.builder()
                .id(set.getInt("id"))
                .name(set.getString("name"))
                .build();
    }
}
