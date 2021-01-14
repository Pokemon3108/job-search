package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;

import java.util.Optional;

public interface UserRoleService {
    void createUser(User user) throws InsertIdDataBaseException, DaoException;
    void deleteUser(int userId) throws PoolException, DaoException;
//    void updateContact(int userId);
//    Optional<Contact> getContact(int userId) throws PoolException, DaoException;
}
