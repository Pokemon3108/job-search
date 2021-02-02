package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.Specialization;

import java.util.List;
import java.util.Optional;

/**
 * The interface Job preference dao.
 */
public interface JobPreferenceDao extends Dao<JobPreference> {
    /**
     * Read id by specialization integer.
     *
     * @param specialization the specialization
     * @return the integer
     * @throws DaoException the dao exception
     */
    Integer readIdBySpecialization(String specialization) throws DaoException;

    /**
     * Read specialization by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Specialization> readSpecializationById(Integer id) throws DaoException;

    /**
     * Read all specializations list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Specialization> readAllSpecializations() throws DaoException;
}
