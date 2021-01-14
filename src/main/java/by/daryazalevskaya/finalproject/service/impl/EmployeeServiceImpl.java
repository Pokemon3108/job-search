package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.*;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.BaseService;
import by.daryazalevskaya.finalproject.service.EmployeeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeServiceImpl extends EmployeeService {
    @Override
    public boolean addNewEntity(Employee entity) throws DaoException, InsertIdDataBaseException {
        boolean isAdded = false;
        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        if (employeeDao.create(entity) != null) {
             isAdded=true;
        }
        return isAdded;
    }

    @Override
    public Optional<Employee> read(int id) throws DaoException, PoolException {
        return Optional.empty();
    }

    @Override
    public void update(Employee entity) throws DaoException, PoolException {

    }

    @Override
    public void delete(int id) throws DaoException, PoolException {
        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        Employee employee=employeeDao.read(id).get();

        ResumeDao resumeDao=transaction.createDao(DaoType.RESUME);
        Resume resume=resumeDao.read(employee.getResume().getId()).get();

        if (Objects.nonNull(resume)) {
            JobPreferenceDao jobPreferenceDao=transaction.createDao(DaoType.JOB_PREFERENCE);
            jobPreferenceDao.delete(resume.getJobPreference().getId());

            EmployeePersonalInfoDao employeePersonalInfoDao=transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
            employeePersonalInfoDao.delete(resume.getPersonalInfo().getId());

            ContactDao contactDao=transaction.createDao(DaoType.CONTACT);
            contactDao.delete(resume.getContact().getId());

            resumeDao.deleteResumeLanguage(resume.getId());
        }
    }

    @Override
    public List<Employee> findAll() throws DaoException, PoolException {
        return null;
    }
}
