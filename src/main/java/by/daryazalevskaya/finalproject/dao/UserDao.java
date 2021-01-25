package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> read(String email) throws DaoException;
}
