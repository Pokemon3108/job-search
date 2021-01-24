package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;

public abstract class UserRoleService extends BaseService  {
    public abstract void createUser(User user) throws DaoException, TransactionException;
    public abstract void deleteUser(Integer userId) throws PoolException, DaoException, TransactionException;
}
