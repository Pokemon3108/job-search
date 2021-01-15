package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.util.List;

public interface ResumeDao extends Dao<Resume> {
    List<Language> getResumeLanguages(int resumeId) throws DaoException;
    void deleteResumeLanguage(int resumeId) throws DaoException;
    void createContact(Resume resume) throws DaoException;
//    void updateJobPreference(Resume resume);


}
