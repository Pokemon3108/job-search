package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.util.List;
import java.util.Optional;


/**
 * The interface Resume dao defines operations with resume table and linked with it
 */
public interface ResumeDao extends Dao<Resume> {
    /**
     * Create contact in resume table
     *
     * @param resume the resume that will be changed in table
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void createContact(Resume resume) throws DaoException;

    /**
     * Create personal info in resume table
     *
     * @param resume the resume that will be changed in table
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void createPersonalInfo(Resume resume) throws DaoException;

    /**
     * Update skills in resume
     *
     * @param resume the resume that will be changed in table
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void updateSkills(Resume resume) throws DaoException;

    /**
     * Create job preference  in resume table
     *
     * @param resume the resume that will be changed in table
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void createJobPreference(Resume resume) throws DaoException;

    /**
     * Create employee language in resume table
     *
     * @param resume the resume that will be changed in table
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void createEmployeeLanguage(Resume resume) throws DaoException;

}
