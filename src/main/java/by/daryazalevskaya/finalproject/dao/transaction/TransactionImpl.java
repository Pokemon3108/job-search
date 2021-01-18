package by.daryazalevskaya.finalproject.dao.transaction;

import by.daryazalevskaya.finalproject.dao.Dao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.impl.*;
import by.daryazalevskaya.finalproject.model.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionImpl implements Transaction {

    private static Map<DaoType, BaseDao> daoMap = new ConcurrentHashMap<>();

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    static {
        daoMap.put(DaoType.CONTACT, new ContactDaoImpl());
        daoMap.put(DaoType.COUNTRY, new CountryDaoImpl());
        daoMap.put(DaoType.EMPLOYEE, new EmployeeDaoImpl());
        daoMap.put(DaoType.EMPLOYEE_PERSONAL_INFO, new EmployeePersonalInfoDaoImpl());
        daoMap.put(DaoType.EMPLOYER, new EmployerDaoImpl());
        daoMap.put(DaoType.JOB_PREFERENCE, new JobPreferenceDaoImpl());
        daoMap.put(DaoType.RESUME, new ResumeDaoImpl());
        daoMap.put(DaoType.USER, new UserDaoImpl());
        daoMap.put(DaoType.VACANCY, new VacancyDaoImpl());
        daoMap.put(DaoType.EMPLOYEE_LANGUAGE, new EmployeeLanguageDaoImpl());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Dao<? extends Entity>> T createDao(DaoType daoType) {
        BaseDao dao=daoMap.get(daoType);
        dao.setConnection(connection);
        return (T)dao;
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void close() throws TransactionException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
