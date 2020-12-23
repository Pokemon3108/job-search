package by.daryazalevskaya.finalproject.service.creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Creator<T> {
    T createEntity(ResultSet set) throws SQLException;
}
