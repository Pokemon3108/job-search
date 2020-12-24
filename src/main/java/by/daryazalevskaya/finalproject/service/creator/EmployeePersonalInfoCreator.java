package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class EmployeePersonalInfoCreator extends Creator<EmployeePersonalInfo> {


    @Override
    public EmployeePersonalInfo createEntity(ResultSet set) throws SQLException {
        return EmployeePersonalInfo.builder()
                .name(set.getString("name"))
                .surname(set.getString("surname"))
                .birthday(LocalDate.ofInstant(set.getDate("experience").toInstant(), ZoneId.systemDefault()))
                .gender(Gender.valueOf(set.getString("gender_type")))
                .city(set.getString("city"))
                .country(new Country(set.getInt("country")))
                .build();
    }
}
