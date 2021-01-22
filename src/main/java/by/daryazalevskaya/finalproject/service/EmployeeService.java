package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.Employee;

import java.util.Optional;

public abstract class EmployeeService  extends UserRoleService {
    public abstract Integer addNewEmployee(Employee employee) throws DaoException, TransactionException;

    public abstract Optional<Employee> read(Integer id) throws DaoException;

    public abstract void update(Employee employee) throws DaoException, InsertIdDataBaseException, TransactionException;

    public abstract void delete(int id) throws DaoException, TransactionException;
}
