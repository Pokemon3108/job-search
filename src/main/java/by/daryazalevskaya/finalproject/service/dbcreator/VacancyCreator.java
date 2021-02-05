package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type VacancyCreator creator is used for creation {@code Vacancy} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employer.Vacancy}
 */
public class VacancyCreator extends Creator<Vacancy> {
    @Override
    public Vacancy createEntity(ResultSet set) throws SQLException {
        Vacancy vacancy = Vacancy.builder()
                .city(set.getString("city"))
                .salary(set.getInt("salary"))
                .currency(Currency.valueOf(set.getString("currency")))
                .position(set.getString("position"))
                .build();

        if (existsColumn(set, "specialization_id")) {
            vacancy.setSpecialization(new Specialization(wasNullId(set, "specialization_id")));
        }

        if (existsColumn(set, "country_id")) {
            vacancy.setCountry(new Country(set.getInt("country_id")));
        }

        if (existsColumn(set, "schedule")) {
            vacancy.setSchedule(Schedule.valueOf(set.getString("schedule")));
        }

        if (existsColumn(set, "duties")) {
            vacancy.setDuties(set.getString("duties"));
        }

        if (existsColumn(set, "requirements")) {
            vacancy.setRequirements(set.getString("requirements"));
        }

        if (existsColumn(set, "employer_id")) {
            vacancy.setEmployer(new Employer(set.getInt("employer_id")));
        }

        if (existsColumn(set, "id")) {
            vacancy.setId(set.getInt("id"));
        }

        return vacancy;
    }

}
