package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    Integer create(T entity) throws InsertIdDataBaseException;
    T read(int id);
    void update(T entity);
    void delete(int id);
    List<T> findAll();
}
