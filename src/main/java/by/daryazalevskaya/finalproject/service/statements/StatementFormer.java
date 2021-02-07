package by.daryazalevskaya.finalproject.service.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The interface Statement former uses for forming statements for database
 *
 * @param <T> the type of entity
 */
public interface StatementFormer<T> {

    /**
     * Fill statement.
     *
     * @param statement the statement that will be filled
     * @param entity    the entity, content of which will be inserted into statement
     * @throws SQLException if occures error with access to database
     */
    void fillStatement(PreparedStatement statement, T entity) throws SQLException;
}