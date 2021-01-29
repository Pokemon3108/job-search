package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.Specialization;

import java.util.List;
import java.util.Optional;

public interface JobPreferenceDao extends Dao<JobPreference> {
    Integer readIdBySpecialization(String specialization) throws DaoException;

    Optional<Specialization> readSpecializationById(int id) throws DaoException;

    List<Specialization> readAllSpecializations() throws DaoException;
}
