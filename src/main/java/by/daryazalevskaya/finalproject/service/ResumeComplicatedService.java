package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;

public abstract class ResumeComplicatedService extends BaseService {
    public abstract void saveEmployeePersonalInfo(Integer userId, JobPreference preference) throws ConnectionException, TransactionException, DaoException;
    public abstract void saveEmployeeLanguage(Integer userId, EmployeeLanguage language) throws TransactionException, DaoException;
    public abstract void saveEmployeePersonalInfo(Integer userId, EmployeePersonalInfo info) throws TransactionException, DaoException;
    public abstract void saveContact(Integer userId, Contact contact) throws DaoException, TransactionException;
}
