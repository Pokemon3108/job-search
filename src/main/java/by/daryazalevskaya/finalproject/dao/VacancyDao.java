package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> findFromTo(int start, int end) throws DaoException;

    int count() throws DaoException;

    List<Vacancy> findVacanciesByEmployerId(Integer id) throws DaoException;

    void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException;

    void addEmployeeVacancy(int vacancyId, int employeeId) throws DaoException;

    List<Vacancy> getEmployeeVacancies(int employeeId) throws DaoException;

    void deleteEmployeeVacancies(int employeeId) throws DaoException;
}
