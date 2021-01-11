package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employer.Employer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployerStatementFormer extends StatementFormer<Employer> {
    @Override
    public void fillStatement(PreparedStatement statement, Employer entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }
}
