package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VacancyStatementFormer extends StatementFormer<Vacancy> {

    @Override
    public void fillStatement(PreparedStatement statement, Vacancy entity) throws SQLException {
        statement.setString(1, entity.getPosition());
        statement.setString(2, entity.getCity());
        statement.setInt(3, entity.getSalary());
        statement.setString(4, entity.getCurrency().toString());
        statement.setString(5, entity.getSchedule().toString());
        statement.setString(6, entity.getDuties());
        statement.setString(7, entity.getRequirements());
        statement.setInt(8, entity.getEmployer().getId());
        statement.setInt(9, entity.getCountry().getId());
        statement.setInt(10, entity.getSpecialization().getId());
        statement.setInt(11, entity.getId());
    }
}
