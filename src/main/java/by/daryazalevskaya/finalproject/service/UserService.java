package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.User;

import java.util.Optional;

public abstract class UserService extends BaseService<User> {
     public abstract boolean isValidLoginAndPassword(String email, String password) throws DaoException;
     public  abstract Optional<User> findUserByEmail(String email) throws DaoException;
}
