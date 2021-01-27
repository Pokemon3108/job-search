package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public abstract class VacancyService extends BaseService {

    public abstract Integer createVacancy(Vacancy vacancy) throws DaoException, TransactionException;

    public abstract Optional<Vacancy> read(Integer id) throws DaoException, TransactionException;

    public abstract void update(Vacancy vacancy) throws DaoException, TransactionException;

    public abstract void delete(Integer id) throws DaoException;

    public abstract List<Vacancy> findAll() throws DaoException;

    public abstract List<Vacancy> findInRange(int start, int end) throws DaoException;

    public abstract int getVacanciesAmount() throws DaoException;

    public abstract void addEmployeeVacancy(Integer vacancyId, Integer employeeId) throws DaoException, TransactionException;

    public abstract boolean hasAlreadyRespond(Integer vacancyId, Integer employeeId) throws DaoException;

    public abstract List<Vacancy> readEmployeeVacancies(Integer employeeId) throws DaoException;

    public abstract List<Vacancy> readVacanciesByEmployerId(Integer id) throws DaoException;

    public abstract void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException, TransactionException;

    public abstract List<Vacancy> readVacancyByParams(VacancySearchParams params, int limit, int offset) throws DaoException;

    public abstract Integer countVacanciesByParams(VacancySearchParams params) throws DaoException;
}

