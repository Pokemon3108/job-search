package by.daryazalevskaya.finalproject.dao.transaction;

import by.daryazalevskaya.finalproject.dao.exception.TransactionException;

public interface TransactionFactory {
    Transaction createTransaction();

    void close() throws TransactionException;

    void commit() throws TransactionException;
}
