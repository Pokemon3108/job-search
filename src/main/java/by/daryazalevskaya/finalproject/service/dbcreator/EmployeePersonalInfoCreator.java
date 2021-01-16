package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeePersonalInfoCreator extends Creator<EmployeePersonalInfo> {

    @Override
    public EmployeePersonalInfo createEntity(ResultSet set) throws SQLException {
        return EmployeePersonalInfo.builder()
                .name(set.getString("name"))
                .surname(set.getString("surname"))
                .birthday(set.getDate("birthday").toLocalDate())
                .gender(Gender.valueOf(set.getString("gender")))
                .city(set.getString("city"))
                .country(new Country(set.getInt("country")))
                .build();
    }
}
