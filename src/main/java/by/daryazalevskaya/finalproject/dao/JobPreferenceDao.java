package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;

public interface JobPreferenceDao extends Dao<JobPreference> {
    Integer findIdBySpecialization(String specialization) throws DaoException;

    String findSpecializationById(int id) throws DaoException;
}
