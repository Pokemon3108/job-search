package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;

import java.util.List;
import java.util.Optional;

public abstract class JobPreferenceService extends BaseService<JobPreference> {
    public abstract Integer findIdBySpecialization(String specialization) throws DaoException;

    public abstract Optional<Specialization> findSpecializationById(int id) throws DaoException;

    public abstract List<Specialization> findAllSpecializations() throws DaoException;
}
