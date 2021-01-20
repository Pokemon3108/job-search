package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.*;

import java.util.List;
import java.util.Optional;

public abstract class ResumeService  extends BaseService<Resume> {
    public abstract Integer createResume(Employee employee) throws DaoException, InsertIdDataBaseException;
    public abstract Optional<Resume> findResumeByUserId(Integer userId) throws PoolException, DaoException;
    public abstract void createContact(Resume resume, Contact contact) throws DaoException;
    public abstract void createPersonalInfo(Resume resume, EmployeePersonalInfo info) throws DaoException;
    public abstract void updateSkills(Resume resume, String skills) throws DaoException;
    public abstract void createJobPreference(Resume resume, JobPreference preference) throws DaoException;
    public abstract void createLanguage(Resume resume, EmployeeLanguage language) throws DaoException;
}

