package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    public BaseService() throws ConnectionException {
        TransactionFactory factory=new TransactionFactoryImpl();
        this.transaction=factory.createTransaction();
    }

    protected Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    //public void setTransaction(Transaction transaction) {
    //    this.transaction = transaction;
   // }

    public abstract boolean addNewEntity(T entity) throws DaoException, InsertIdDataBaseException;

    public abstract Optional<T> read(int id) throws DaoException, PoolException;

    public abstract void update(T entity) throws DaoException, PoolException;

    public abstract void delete(int id) throws DaoException, PoolException;

    public abstract List<T> findAll() throws DaoException, PoolException;
}
