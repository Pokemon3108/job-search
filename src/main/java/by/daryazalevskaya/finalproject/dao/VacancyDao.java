package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> findFromTo(int start, int end) throws DaoException;
    int count() throws DaoException;
    List<Vacancy> findVacanciesByEmployerId(Integer id) throws DaoException;
}
