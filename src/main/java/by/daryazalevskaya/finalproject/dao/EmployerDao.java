package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;

public interface EmployerDao extends Dao<Employer> {
    void createContact(Integer employerId, Integer contactId) throws DaoException;

    Integer readUserIdByCompany(String company) throws DaoException;
}
