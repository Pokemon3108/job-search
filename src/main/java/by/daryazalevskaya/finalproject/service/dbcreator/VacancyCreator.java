package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class VacancyCreator extends Creator<Vacancy> {
    @Override
    public Vacancy createEntity(ResultSet set) throws SQLException {
        Vacancy vacancy = Vacancy.builder()
                .city(set.getString("city"))
                .salary(set.getInt("salary"))
                .currency(Currency.valueOf(set.getString("currency")))
                .duties(set.getString("duties"))
                .requirements(set.getString("requirements"))
                .schedule(Schedule.valueOf(set.getString("schedule")))
                .position(set.getString("position"))
                .build();

        if (existsColumn(set, "employer_id")) {
            vacancy.setEmployer(new Employer(set.getInt("employer_id")));
        }

        if (existsColumn(set, "id")) {
            vacancy.setId(set.getInt("id"));
        }

        return vacancy;
    }

}
