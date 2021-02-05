package by.daryazalevskaya.finalproject.service.dbcreator;


import by.daryazalevskaya.finalproject.model.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type SpecializationCreator creator is used for creation {@code Specialization} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.Specialization}
 */
public class SpecializationCreator extends Creator<Specialization> {
    @Override
    public Specialization createEntity(ResultSet set) throws SQLException {
        return Specialization.builder()
                .id(set.getInt("id"))
                .name(set.getString("name"))
                .build();
    }
}
