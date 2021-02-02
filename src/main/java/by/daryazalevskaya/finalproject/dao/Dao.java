package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Entity;

import java.util.List;
import java.util.Optional;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter
 */
public interface Dao<T extends Entity> {
    /**
     * Create record in database.
     *
     * @param entity the entity that inserts to databse
     * @return id of inserted record
     * @throws InsertIdDataBaseException is thrown when id wasn't generated after inserting in database
     * @throws DaoException   is thrown when occured error with access to database
     */
    Integer create(T entity) throws InsertIdDataBaseException, DaoException;

    /**
     * Read record from database
     *
     * @param id the id
     * @return the optional
     * @throws DaoException  is thrown when occured error with access to database
     */
    Optional<T> read(Integer id) throws DaoException;

    /**
     * Update record in database
     *
     * @param entity is a record that will be updated
     * @throws DaoException  is thrown when occured error with access to database
     */
    void update(T entity) throws DaoException;

    /**
     * Delete record from database
     *
     * @param id of record that will be deleted
     * @throws DaoException  is thrown when occured error with access to database
     */
    void delete(Integer id) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException  is thrown when occured error with access to database
     */
    List<T> findAll() throws DaoException;
}
