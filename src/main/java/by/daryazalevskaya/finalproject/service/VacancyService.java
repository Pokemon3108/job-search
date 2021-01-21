package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public abstract class VacancyService extends BaseService {
    public abstract List<Vacancy> findVacanciesByEmployerId(int id) throws DaoException;

    public abstract void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException, TransactionException;

    public abstract Integer addNewVacancy(Vacancy vacancy) throws DaoException, TransactionException;

    public abstract Optional<Vacancy> read(Integer id) throws DaoException, TransactionException;

    public abstract void update(Vacancy vacancy) throws DaoException, TransactionException;

    public abstract void delete(int id) throws DaoException;

    public abstract List<Vacancy> findAll() throws DaoException;
}

