package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JobPreferenceStatementFormer implements StatementFormer<JobPreference> {


    @Override
    public void fillStatement(PreparedStatement statement, JobPreference entity) throws SQLException {
        statement.setString(1, entity.getPosition());
        setNull(2, entity.getSalary(), statement);
        statement.setString(3, entity.getCurrency().toString());
        statement.setInt(4, entity.getSpecialization().getId());
        statement.setString(5, entity.getSchedule().toString());
        setNull(6, entity.getExperience(), statement);

    }

    private void setNull(int index, Integer value, PreparedStatement statement) throws SQLException {
        if (value==null) {
            statement.setNull(index, java.sql.Types.NULL);
        } else {
            statement.setInt(index, value);
        }
    }
}
