package by.daryazalevskaya.finalproject.service.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The functional interface defines operation for filling statement
 */
@FunctionalInterface
public interface SearchVacancyFormer {
    /**
     * Fill statement.
     *
     * @param statement the statement that will be filled
     * @throws SQLException if occures error with access to database
     */
    void fillStatement(PreparedStatement statement) throws SQLException;
}
