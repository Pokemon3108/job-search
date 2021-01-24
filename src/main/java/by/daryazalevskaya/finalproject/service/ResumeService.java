package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.*;

import java.util.List;
import java.util.Optional;

public abstract class ResumeService extends BaseService {
    public abstract Integer createResume(Employee employee) throws DaoException, TransactionException;

    public abstract Optional<Resume> findResumeByUserId(Integer userId) throws  DaoException;

    public abstract void createContact(Resume resume, Contact contact) throws DaoException, TransactionException;

    public abstract void createPersonalInfo(Resume resume, EmployeePersonalInfo info) throws DaoException, TransactionException;

    public abstract void updateSkills(Resume resume, String skills) throws DaoException, TransactionException;

    public abstract void createJobPreference(Resume resume, JobPreference preference) throws DaoException, TransactionException;

    public abstract void createLanguage(Resume resume, EmployeeLanguage language) throws DaoException, TransactionException;

    public abstract Optional<Resume> read(Integer id) throws DaoException, TransactionException;

    public abstract void update(Resume resume) throws DaoException, TransactionException;

    public abstract void delete(Integer id) throws DaoException, TransactionException;

    public abstract List<Resume> findAll() throws DaoException;

    public abstract void fillResume(Resume resume) throws DaoException;
}

