package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;

import java.util.List;
import java.util.Optional;

/**
 * The interface Job preference dao defines operations with job preference table and specialization table
 */
public interface JobPreferenceDao extends Dao<JobPreference> {
    /**
     * Read id by specialization name
     * @param specialization the specialization name
     * @return the id of specialization
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer readIdBySpecialization(String specialization) throws DaoException;

    /**
     * Read specialization by id
     * @param id the id of specialization
     * @return the {@code Specialization} object found by id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Optional<Specialization> readSpecializationById(Integer id) throws DaoException;

    /**
     * Read all specializations from specializations catalog
     * @return the list of specializations
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Specialization> readAllSpecializations() throws DaoException;
}
