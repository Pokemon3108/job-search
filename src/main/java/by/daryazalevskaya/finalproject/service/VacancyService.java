package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;

public abstract class VacancyService extends BaseService<Vacancy> {
    public abstract List<Vacancy> findVacanciesByEmployerId(int id) throws DaoException;
    public abstract void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException;
}

