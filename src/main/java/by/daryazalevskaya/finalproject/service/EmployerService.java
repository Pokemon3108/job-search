package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;
import java.util.Optional;

public abstract class EmployerService extends  UserRoleService{
    public abstract void createContact(int employerId, Contact contact) throws DaoException;

    public abstract Integer createEmployer(Employer  employer) throws DaoException, TransactionException;

    public abstract Optional<Employer> read(Integer id) throws DaoException;

    public abstract void update(Employer employer) throws DaoException, TransactionException;

    public abstract void delete(Integer id) throws DaoException, TransactionException;

    public abstract boolean containsCompanyName(String company, Integer userId) throws DaoException;

}
