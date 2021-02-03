package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.Employee;

import java.util.List;

/**
 * The interface Employee dao defines operations with employee table
 */
public interface EmployeeDao extends Dao<Employee> {

    /**
     * Read records from dao in range
     *
     * @param start the start border
     * @param end   the end border
     * @return the list of employee
     * @throws DaoException is thrown when occures error with access to database
     */
    List<Employee> readFromTo(int start, int end) throws DaoException;

    /**
     * Count amount of employees in database
     * @return amount of employees
     * @throws DaoException is thrown when occured error with access to database
     */
    int count() throws DaoException;

}
