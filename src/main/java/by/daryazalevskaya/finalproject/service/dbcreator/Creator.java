package by.daryazalevskaya.finalproject.service.dbcreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class Creator<T> {

    public abstract T createEntity(ResultSet set) throws SQLException;
}
