package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employee.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LanguageStatementFormer extends StatementFormer<Language> {
    @Override
    public void fillStatement(PreparedStatement statement, Language entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getLevel().toString());
    }
}
