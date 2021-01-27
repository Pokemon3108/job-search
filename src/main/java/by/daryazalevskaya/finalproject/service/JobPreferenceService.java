package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.Specialization;

import java.util.List;
import java.util.Optional;

public abstract class JobPreferenceService extends BaseService {

    public abstract Integer createJobPreference(JobPreference preference) throws DaoException, TransactionException;

    public abstract Optional<JobPreference> read(Integer id) throws DaoException;

    public abstract void update(JobPreference preference) throws DaoException, TransactionException;

    public abstract void delete(Integer id) throws DaoException, TransactionException;

    public abstract Integer findIdBySpecialization(String specialization) throws DaoException;

    public abstract Optional<Specialization> findSpecializationById(Integer id) throws DaoException;

    public abstract List<Specialization> findAllSpecializations() throws DaoException;

}
