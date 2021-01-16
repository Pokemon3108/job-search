package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.util.Optional;

public abstract class ResumeService  extends BaseService<Resume> {
    public abstract Integer createResume(Employee employee) throws DaoException, InsertIdDataBaseException;
    public abstract Optional<Resume> findResumeByUserId(Integer userId) throws PoolException, DaoException;
    public abstract void createContact(Resume resume) throws DaoException;
    public abstract void createPersonalInfo(Resume resume) throws DaoException;
}
