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

    public abstract List<Vacancy> findInRange(int start, int end) throws DaoException;

    public abstract int getVacanciesSize() throws DaoException;

    public abstract void addEmployeeVacancy(int vacancyId, int employeeId) throws DaoException;

    public abstract boolean hasAlreadyRespond(int vacancyId, int employeeId) throws DaoException;

    public abstract List<Vacancy> findEmployeeVacancies(int employeeId) throws DaoException;
}
