package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;

public interface EmployeeDao extends Dao<Employee>  {
    List<Vacancy> getEmployeeVacancies(int employeeId) throws DaoException;
    List<Employee> findFromTo(int start, int end) throws DaoException;
    int count() throws DaoException;
    void deleteEmployeeVacancies(int employeeId) throws DaoException;
}
