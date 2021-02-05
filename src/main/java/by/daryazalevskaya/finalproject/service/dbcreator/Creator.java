package by.daryazalevskaya.finalproject.service.dbcreator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * The type Creator uses for creation objects from result set after executing SQL statement
 *
 * @param <T> the type of object that will be created
 */
public abstract class Creator<T> {

    /**
     * Create entity.
     *
     * @param set the result set after executing  sql statement
     * @return created object
     * @throws SQLException if occures error with access to database
     */
    public abstract T createEntity(ResultSet set) throws SQLException;

    /**
     * Check if column with {@code columnName} exists in {@code ResultSet} set
     *
     * @param set the result set after executing  sql statement
     * @param columnName the column name
     * @return true if column with {@code columnName} exists, else - false
     * @throws SQLException if occures error with access to database
     */
    protected boolean existsColumn(ResultSet set, String columnName) throws SQLException {
        ResultSetMetaData metaData = set.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++)
            if (columnName.equals(metaData.getColumnName(i)))
                return true;

        return false;
    }

    /**
     * The integer value from {@code resultSet} by {@code columnName}
     *
     * @param resultSet the result set after executing  sql statement
     * @param columnName the name of column that will be checked
     * @return null if columnName doesn't contain in {@code resultSet}, else - its integer value
     * @throws SQLException the sql exception
     */
    protected Integer wasNullId(ResultSet resultSet, String columnName) throws SQLException {
        Integer value=resultSet.getInt(columnName);
        if (resultSet.wasNull()) {
            value=null;
        }
        return value;
    }
}
