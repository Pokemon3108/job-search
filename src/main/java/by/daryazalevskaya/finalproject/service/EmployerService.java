package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employer.Employer;

public abstract class EmployerService extends BaseService<Employer> implements UserRoleService{
    public abstract void createContact(int employerId, Contact contact) throws DaoException;
}
