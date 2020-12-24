package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JobPreferenceStatementFormer extends StatementFormer<JobPreference> {
    private int foreignKey;

    public JobPreferenceStatementFormer(int foreignKey) {
        this.foreignKey = foreignKey;
    }

    @Override
    public void fillStatement(PreparedStatement statement, JobPreference entity) throws SQLException {
        statement.setString(1, entity.getDesiredPosition());
        statement.setInt(2, entity.getSalary());
        statement.setString(3, entity.getCurrency().toString());
        statement.setInt(4, foreignKey);
        statement.setString(5, entity.getSchedule().toString());
        statement.setInt(6, entity.getExperience());
    }
}
