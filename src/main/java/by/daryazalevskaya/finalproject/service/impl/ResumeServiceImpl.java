package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.ResumeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.*;
import by.daryazalevskaya.finalproject.service.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResumeServiceImpl extends ResumeService {

    @Override
    public Optional<Resume> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        Optional<Resume> resume = resumeDao.read(id);
        if (resume.isPresent()) {
            fillResume(resume.get());
        }
        return resume;
    }

    @Override
    public void update(Resume entity) throws DaoException, TransactionException {
        try {
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.update(entity);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.delete(id);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Resume> findAll() throws DaoException {
        ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
        List<Resume> resumeList = resumeDao.findAll();
        resumeList = resumeList.stream().filter(resume -> resume.getPersonalInfo().getId() != null
                && resume.getJobPreference().getId() != null
                && (resume.getSkills()!=null || !resume.getSkills().isEmpty())).collect(Collectors.toList());
        for (Resume resume : resumeList) {
            fillResume(resume);
        }
        return resumeList;
    }

    @Override
    public void fillResume(Resume resume) throws DaoException {
        ContactService contactService = new ContactServiceImpl();
        contactService.setTransaction(transaction);
        Optional<Contact> contact = contactService.read(resume.getContact().getId());
        contact.ifPresent(resume::setContact);

        EmployeePersonalInfoService infoService = new EmployeePersonalInfoServiceImpl();
        infoService.setTransaction(transaction);
        Optional<EmployeePersonalInfo> info = infoService.read(resume.getPersonalInfo().getId());
        info.ifPresent(resume::setPersonalInfo);

        JobPreferenceService preferenceService = new JobPreferenceServiceImpl();
        preferenceService.setTransaction(transaction);
        Optional<JobPreference> preference = preferenceService.read(resume.getJobPreference().getId());
        preference.ifPresent(resume::setJobPreference);

        EmployeeLanguageService languageService = new EmployeeLanguageServiceImpl();
        languageService.setTransaction(transaction);
        Optional<EmployeeLanguage> language = languageService.read(resume.getLanguage().getId());
        language.ifPresent(resume::setLanguage);
    }

    @Override
    public Integer createResume(Employee employee) throws DaoException, TransactionException {
        try {
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            Resume resume = new Resume();
            resume.setUser(new User(employee.getId()));
            return resumeDao.create(resume);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Resume> findResumeByUserId(Integer userId) throws DaoException {
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
    public void createContact(Resume resume, Contact contact) throws DaoException, TransactionException {
        try {
            resume.setContact(contact);
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.createContact(resume);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void createPersonalInfo(Resume resume, EmployeePersonalInfo info) throws DaoException, TransactionException {
        try {
            resume.setPersonalInfo(info);
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.createPersonalInfo(resume);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void updateSkills(Resume resume, String skills) throws DaoException, TransactionException {
        try {
            resume.setSkills(skills);
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.updateSkills(resume);
            transaction.commit();
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void createJobPreference(Resume resume, JobPreference preference) throws DaoException, TransactionException {
        try {
            resume.setJobPreference(preference);
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.createJobPreference(resume);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void createLanguage(Resume resume, EmployeeLanguage language) throws DaoException, TransactionException {
        try {
            resume.setLanguage(language);
            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            resumeDao.createLanguage(resume);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }
}
