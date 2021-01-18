package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;

public interface EmployerDao extends Dao<Employer> {
    void createContact(int employerId, int contactId) throws DaoException;
}
