package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.Specialization;

import java.util.List;
import java.util.Optional;

public interface JobPreferenceDao extends Dao<JobPreference> {
    Integer findIdBySpecialization(String specialization) throws DaoException;

    Optional<Specialization> findSpecializationById(int id) throws DaoException;


    List<Specialization> findAllSpecializations() throws DaoException;
}
