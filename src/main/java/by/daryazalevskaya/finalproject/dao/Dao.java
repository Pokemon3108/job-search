package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    Integer create(T entity) throws InsertIdDataBaseException, DaoException, IllegalOperationException;
    Optional<T> read(int id) throws DaoException, IllegalOperationException;
    void update(T entity) throws DaoException, IllegalOperationException;
    void delete(int id) throws DaoException, IllegalOperationException;
    List<T> findAll() throws DaoException, IllegalOperationException;
}
