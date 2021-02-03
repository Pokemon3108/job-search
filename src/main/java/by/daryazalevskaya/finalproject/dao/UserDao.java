package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.User;

import java.util.Optional;


/**
 * The interface User dao defines operations with usr table
 */
public interface UserDao extends Dao<User> {
    /**
     * Read user by his email
     *
     * @param email the email, that user will be searched for
     * @return the user object with email
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Optional<User> read(String email) throws DaoException;
}
