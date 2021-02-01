package by.daryazalevskaya.finalproject.service.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SearchVacancyFormer {
    void fillStatement(PreparedStatement statement) throws SQLException;
}
