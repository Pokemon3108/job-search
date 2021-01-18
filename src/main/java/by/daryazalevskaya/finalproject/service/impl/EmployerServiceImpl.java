package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployerService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

;

public class EmployerServiceImpl extends EmployerService {

    @Override
    public Integer addNewEntity(Employer entity) throws DaoException, InsertIdDataBaseException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        employerDao.create(entity);
        return entity.getId();
    }

    @Override
    public Optional<Employer> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        return employerDao.read(id);
    }

    @Override
    public void update(Employer entity) throws DaoException, PoolException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
         employerDao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        Employer employer = employerDao.read(id).orElse(null);

        if (Objects.nonNull(employer)) {
            ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
            contactDao.delete(employer.getId());

            VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
            vacancyDao.delete(employer.getId());
        }
        employerDao.delete(id);
    }

    @Override
    public List<Employer> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public void createUser(User user) throws InsertIdDataBaseException, DaoException {
        Employer employer = new Employer(user.getId());
        addNewEntity(employer);
    }

    @Override
    public void deleteUser(int userId) throws PoolException, DaoException {
        delete(userId);
    }

    @Override
    public void createContact(int employerId, Contact contact) throws DaoException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        employerDao.createContact(employerId, contact.getId());
    }

}
