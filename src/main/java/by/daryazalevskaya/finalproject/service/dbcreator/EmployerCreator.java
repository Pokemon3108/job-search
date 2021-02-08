package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employer.Employer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type EmployerCreator creator is used for creation {@code Employer} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employer.Employer}
 */
public class EmployerCreator extends Creator<Employer> {
    @Override
    public Employer createEntity(ResultSet set) throws SQLException {
        return  Employer.builder()
                .city(set.getString("city"))
                .companyName(set.getString("company_name"))
                .contact(new Contact(set.getInt("contact_id")))
                .country(new Country(wasNullInt(set, "country")))
                .build();

    }
}
