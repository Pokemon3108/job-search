package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employer.Employer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployerCreator extends Creator<Employer> {
    @Override
    public Employer createEntity(ResultSet set) throws SQLException {
        return  Employer.builder()
                .city(set.getString("city"))
                .companyName(set.getString("company_name"))
                .contact(new Contact(set.getInt("contact_id")))
                .country(new Country(wasNull(set, "country")))
                .build();

    }
}
