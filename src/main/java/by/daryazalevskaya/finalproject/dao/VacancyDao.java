package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> findFromTo(int start, int end) throws DaoException;
    int count() throws DaoException;
}
