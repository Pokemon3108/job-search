package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    Integer create(T entity) throws InsertIdDataBaseException, DaoException;
    T read(int id) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(int id) throws DaoException;
    List<T> findAll() throws DaoException;
}
