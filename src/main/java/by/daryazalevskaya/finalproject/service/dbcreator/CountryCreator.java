package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryCreator extends Creator<Country> {
    @Override
    public Country createEntity(ResultSet set) throws SQLException {
        return Country.builder()
                .name(set.getString("name"))
                .id(set.getInt("id"))
                .build();
    }
}
