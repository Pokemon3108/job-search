package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.transaction.Transaction;

public abstract class BaseService implements Service {

    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
