package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VacancyStatementFormer extends StatementFormer<Vacancy> {

    @Override
    public void fillStatement(PreparedStatement statement, Vacancy entity) throws SQLException {
        statement.setString(1, entity.getPosition());
        statement.setString(2, entity.getCity());
        statement.setInt(3, entity.getMinExperience());
        statement.setString(4, entity.getSchedule().toString());
        statement.setString(5, entity.getDuties());
        statement.setString(6, entity.getRequirements());
        statement.setInt(7, entity.getEmployer().getId());
    }
}
