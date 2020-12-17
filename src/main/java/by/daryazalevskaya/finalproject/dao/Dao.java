package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    void create(T entity);
    T read(int id);
    void update(T entity);
    void delete(int id);
    List<T> findAll();
}
