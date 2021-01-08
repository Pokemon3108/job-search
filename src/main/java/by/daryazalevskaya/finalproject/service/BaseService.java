package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    protected abstract Integer create(T entity);
    protected abstract Optional<T> read(int id);
    protected abstract void update(T entity);
    protected abstract void delete(int id);
    protected abstract List<T> findAll();
}
