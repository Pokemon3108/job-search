package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    Integer create(T entity) throws InsertIdDataBaseException, DaoException;
    Optional<T> read(Integer id) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(Integer id) throws DaoException;
    List<T> findAll() throws DaoException;
}
