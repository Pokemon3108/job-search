package by.daryazalevskaya.finalproject.dao.transaction;

import by.daryazalevskaya.finalproject.dao.Dao;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.DaoType;

public interface Transaction {
    <T extends Dao<? extends Entity>> T createDao(DaoType daoType);

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

    void close() throws TransactionException;
}
