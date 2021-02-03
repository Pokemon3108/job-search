package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;


/**
 * The interface Employer dao defines operations with employer table and linked tables
 */
public interface EmployerDao extends Dao<Employer> {
    /**
     * Create contact.
     *
     * @param employerId the employer id
     * @param contactId  the contact id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void createContact(Integer employerId, Integer contactId) throws DaoException;

    /**
     * Read user id by company integer.
     *
     * @param company the company
     * @return the integer
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer readUserIdByCompany(String company) throws DaoException;
}
