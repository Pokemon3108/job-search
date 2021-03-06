package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;

import java.util.List;
import java.util.Optional;

public abstract class UserService extends BaseService {

    public abstract Integer createUser(User user) throws DaoException, InsertIdDataBaseException, TransactionException;

    public abstract Optional<User> read(Integer id) throws DaoException;

    public abstract void update(User user) throws DaoException, TransactionException;

    public abstract void delete(Integer id) throws DaoException, TransactionException;

    public abstract List<User> findAll() throws DaoException;

    public abstract boolean isValidLoginAndPassword(String email, String password) throws DaoException;

    public abstract Optional<User> findUserByEmail(String email) throws DaoException;

    public abstract boolean userExists(String email) throws DaoException;
}
