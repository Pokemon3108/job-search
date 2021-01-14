package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;

public abstract class ResumeService  extends BaseService<Resume> {
    public abstract Integer createResume(Employee employee) throws DaoException, InsertIdDataBaseException;
}
