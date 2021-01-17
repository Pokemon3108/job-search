package by.daryazalevskaya.finalproject.service.dbcreator;


import by.daryazalevskaya.finalproject.model.employee.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationCreator extends Creator<Specialization> {
    @Override
    public Specialization createEntity(ResultSet set) throws SQLException {
        return Specialization.builder()
                .id(set.getInt("id"))
                .name(set.getString("name"))
                .build();
    }
}
