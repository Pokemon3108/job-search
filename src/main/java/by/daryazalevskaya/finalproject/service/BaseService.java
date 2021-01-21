package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.transaction.Transaction;

public abstract class BaseService implements Service {

    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

//    public abstract <T extends Entity> Integer addNewEntity(T entity) throws DaoException, InsertIdDataBaseException;
//
//    public abstract <T extends Entity> Optional<T> read(Integer id) throws DaoException, PoolException;
//
//    public abstract <T extends Entity> void update(T entity) throws DaoException, PoolException, InsertIdDataBaseException;
//
//    public abstract void delete(int id) throws DaoException, PoolException;
//
//    public abstract <T extends Entity> List<T> findAll() throws DaoException, PoolException;
}
