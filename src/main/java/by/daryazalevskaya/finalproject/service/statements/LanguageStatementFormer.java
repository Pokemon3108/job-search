package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LanguageStatementFormer extends StatementFormer<EmployeeLanguage> {
    @Override
    public void fillStatement(PreparedStatement statement, EmployeeLanguage entity) throws SQLException {
        statement.setInt(1, entity.getName().getId());
        statement.setString(2, entity.getLevel().toString());
    }
}
