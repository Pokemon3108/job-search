package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.*;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.EmployeeService;
import by.daryazalevskaya.finalproject.service.ResumeService;

import java.util.Objects;
import java.util.Optional;

public class EmployeeServiceImpl extends EmployeeService {
    @Override
    public Integer createEmployee(Employee employee) throws DaoException, TransactionException {
        try {
            ResumeService resumeService = new ResumeServiceImpl();
            resumeService.setTransaction(transaction);
            Integer resumeId = resumeService.createResume(employee);

            employee.setResume(new Resume(resumeId));

            EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
            return employeeDao.create(employee);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }


    @Override
    public Optional<Employee> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        Optional<Employee> employee;
        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        employee = employeeDao.read(id);
        return employee;
    }

    @Override
    public void update(Employee entity) throws DaoException, TransactionException {
        try {
            EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
            employeeDao.update(entity);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
            Optional<Employee> employee = employeeDao.read(id);

            if (employee.isPresent()) {

                ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
                Resume resume = resumeDao.read(employee.get().getResume().getId()).orElse(null);

                if (Objects.nonNull(resume)) {

                    VacancyDao vacancyDao=transaction.createDao(DaoType.VACANCY);
                    vacancyDao.deleteEmployeeVacanciesByEmployeeId(id);

                    employeeDao.delete(id);
                    resumeDao.delete(resume.getId());

                    if (resume.getJobPreference().getId() != null) {
                        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
                        jobPreferenceDao.delete(resume.getJobPreference().getId());
                    }

                    if (resume.getPersonalInfo().getId() != null) {
                        EmployeePersonalInfoDao employeePersonalInfoDao = transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
                        employeePersonalInfoDao.delete(resume.getPersonalInfo().getId());
                    }

                    if (resume.getContact().getId() != null) {
                        ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
                        contactDao.delete(resume.getContact().getId());
                    }

                    if (resume.getLanguage().getId() != null) {
                        EmployeeLanguageDao employeeLanguageDao = transaction.createDao(DaoType.EMPLOYEE_LANGUAGE);
                        employeeLanguageDao.delete(resume.getLanguage().getId());
                    }


                }

            }
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void createUser(User user) throws DaoException, TransactionException {
        Employee employee = new Employee(user.getId());
        createEmployee(employee);
    }

    @Override
    public void deleteUser(Integer userId) throws DaoException, TransactionException {
        delete(userId);
        transaction.commit();
    }

}
