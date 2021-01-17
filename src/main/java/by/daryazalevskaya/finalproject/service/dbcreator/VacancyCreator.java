package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacancyCreator extends Creator<Vacancy> {
    @Override
    public Vacancy createEntity(ResultSet set) throws SQLException {
        return Vacancy.builder()
                .city(set.getString("city"))
                .minExperience(set.getInt("min_experience"))
                .duties(set.getString("duties"))
                .requirements(set.getString("requirements"))
                .employer(new Employer(set.getInt("employer_id")))
                .schedule(Schedule.valueOf(set.getString("schedule_type")))
                .position(set.getString("position"))
                .build();
    }
}
