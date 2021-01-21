package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public abstract class JobPreferenceService extends BaseService {
    public abstract Integer findIdBySpecialization(String specialization) throws DaoException;

    public abstract Optional<Specialization> findSpecializationById(int id) throws DaoException;

    public abstract List<Specialization> findAllSpecializations() throws DaoException;

    public abstract Integer addNewJobPreference(JobPreference preference) throws DaoException, TransactionException;

    public abstract Optional<JobPreference> read(Integer id) throws DaoException;

    public abstract void update(JobPreference preference) throws DaoException, TransactionException;

    public abstract void delete(int id) throws DaoException, TransactionException;

}
