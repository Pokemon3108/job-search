package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployeeDao;
import by.daryazalevskaya.finalproject.dao.EmployeePersonalInfoDao;
import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.ResumeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.EmployeeService;
import by.daryazalevskaya.finalproject.service.ResumeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeServiceImpl extends EmployeeService {
    @Override
    public Integer addNewEntity(Employee entity) throws DaoException, InsertIdDataBaseException {
        ResumeService resumeService = new ResumeServiceImpl();
        resumeService.setTransaction(transaction);
        Integer resumeId = resumeService.createResume(entity);

        entity.setResume(new Resume(resumeId));

        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        return employeeDao.create(entity);

    }


    @Override
    public Optional<Employee> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        Optional<Employee> employee;
        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        employee = employeeDao.read(id);
        return employee;
    }

    @Override
    public void update(Employee entity) throws DaoException, PoolException {

    }

    @Override
    public void delete(int id) throws DaoException, PoolException {
        EmployeeDao employeeDao = transaction.createDao(DaoType.EMPLOYEE);
        Optional<Employee> employee = employeeDao.read(id);

        if (employee.isPresent()) {

            ResumeDao resumeDao = transaction.createDao(DaoType.RESUME);
            Resume resume = resumeDao.read(employee.get().getResume().getId()).orElse(null);

            if (Objects.nonNull(resume)) {
                JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
                jobPreferenceDao.delete(resume.getJobPreference().getId());

                EmployeePersonalInfoDao employeePersonalInfoDao = transaction.createDao(DaoType.EMPLOYEE_PERSONAL_INFO);
                employeePersonalInfoDao.delete(resume.getPersonalInfo().getId());

                ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
                contactDao.delete(resume.getContact().getId());

                resumeDao.deleteResumeLanguage(resume.getId());
            }

            employeeDao.delete(id);
        }
    }

    @Override
    public List<Employee> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public void createUser(User user) throws InsertIdDataBaseException, DaoException {
        Employee employee = new Employee(user.getId());
        addNewEntity(employee);
    }

    @Override
    public void deleteUser(int userId) throws PoolException, DaoException {
        delete(userId);
    }


}
