package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SearchVacancyFormer {
    void fillStatement(PreparedStatement statement) throws SQLException;
}
