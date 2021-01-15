package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract Integer addNewEntity(T entity) throws DaoException, InsertIdDataBaseException;

    public abstract Optional<T> read(int id) throws DaoException, PoolException;

    public abstract void update(T entity) throws DaoException, PoolException, InsertIdDataBaseException;

    public abstract void delete(int id) throws DaoException, PoolException;

    public abstract List<T> findAll() throws DaoException, PoolException;
}
