package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeDao extends Dao<Resume> {
    void createContact(Resume resume) throws DaoException;

    void createPersonalInfo(Resume resume) throws DaoException;

    void updateSkills(Resume resume) throws DaoException;

    void createJobPreference(Resume resume) throws DaoException;

    void createLanguage(Resume resume) throws DaoException;

}
