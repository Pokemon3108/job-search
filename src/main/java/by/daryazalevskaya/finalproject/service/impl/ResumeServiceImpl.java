package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.ResumeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.*;
import by.daryazalevskaya.finalproject.service.EmployeeService;
import by.daryazalevskaya.finalproject.service.ResumeService;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResumeServiceImpl extends ResumeService {
    @Override
    public Integer addNewEntity(Resume entity) throws DaoException, InsertIdDataBaseException {
        return null;
    }

    @Override
    public Optional<Resume> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        return resumeDao.read(id);
    }

    @Override
    public void update(Resume entity) throws DaoException, PoolException, InsertIdDataBaseException {
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<Resume> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public Integer createResume(Employee employee) throws DaoException, InsertIdDataBaseException {
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        Resume resume = new Resume();
        resume.setUser(new User(employee.getId()));
        return resumeDao.create(resume);
    }

    @Override
    public Optional<Resume> findResumeByUserId(Integer userId) throws PoolException, DaoException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.setTransaction(transaction);
        Optional<Employee> employee = employeeService.read(userId);
        if (employee.isPresent()) {
            return read(employee.get().getResume().getId());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void createContact(Resume resume, Contact contact) throws DaoException {
        resume.setContact(contact);
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.createContact(resume);
    }

    @Override
    public void createPersonalInfo(Resume resume, EmployeePersonalInfo info) throws DaoException {
        resume.setPersonalInfo(info);
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.createPersonalInfo(resume);
    }

    @Override
    public void updateSkills(Resume resume, String skills) throws DaoException {
        resume.setSkills(skills);
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.updateSkills(resume);
    }

    @Override
    public void createJobPreference(Resume resume, JobPreference preference) throws DaoException {
        resume.setJobPreference(preference);
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.createJobPreference(resume);
    }

    @Override
    public void createLanguage(Resume resume, EmployeeLanguage language) throws DaoException {
        resume.setLanguage(language);
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        resumeDao.createLanguage(resume);
    }


}
