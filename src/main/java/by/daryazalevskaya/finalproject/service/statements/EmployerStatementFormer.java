package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employer.Employer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployerStatementFormer extends StatementFormer<Employer> {
    @Override
    public void fillStatement(PreparedStatement statement, Employer entity) throws SQLException {
        statement.setString(1, entity.getCompanyName());
        statement.setInt(2, entity.getCountry().getId());
        statement.setString(3, entity.getCity());
        statement.setInt(4, entity.getId());
    }
}
