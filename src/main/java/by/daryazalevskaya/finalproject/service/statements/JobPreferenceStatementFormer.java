package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JobPreferenceStatementFormer implements StatementFormer<JobPreference> {


    @Override
    public void fillStatement(PreparedStatement statement, JobPreference entity) throws SQLException {
        statement.setString(1, entity.getPosition());
        statement.setInt(2, entity.getSalary());
        statement.setString(3, entity.getCurrency().toString());
        statement.setInt(4, entity.getSpecialization().getId());
        statement.setString(5, entity.getSchedule().toString());
        statement.setInt(6, entity.getExperience());
    }
}
