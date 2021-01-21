package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;

import java.util.Optional;

public abstract class UserRoleService extends BaseService  {
    public abstract void createUser(User user) throws DaoException, TransactionException;
    public abstract void deleteUser(int userId) throws PoolException, DaoException, TransactionException;
}
