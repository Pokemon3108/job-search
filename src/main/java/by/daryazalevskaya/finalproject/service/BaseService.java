package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract boolean addNewEntity(T entity) throws DaoException, InsertIdDataBaseException;

    public abstract Optional<T> read(int id) throws DaoException;

    public abstract void update(T entity) throws DaoException;

    public abstract void delete(int id) throws DaoException;

    public abstract List<T> findAll() throws DaoException;
}
