package by.daryazalevskaya.finalproject.service.dbcreator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class Creator<T> {

    public abstract T createEntity(ResultSet set) throws SQLException;

    protected boolean existsColumn(ResultSet set, String columnName) throws SQLException {
        ResultSetMetaData metaData = set.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++)
            if (columnName.equals(metaData.getColumnName(i)))
                return true;

        return false;
    }
}
