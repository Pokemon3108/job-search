package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ResumeStatementFormer extends StatementFormer<Resume> {
    @Override
    public void fillStatement(PreparedStatement statement, Resume entity) throws SQLException {
        statement.setString(1, entity.getSkills());
        statement.setInt(2, entity.getEmployee().getId());
        statement.setInt(3, entity.getContact().getId());
        statement.setInt(4, entity.getPersonalInfo().getId());
        statement.setInt(5, entity.getJobPreference().getId());
    }
}
