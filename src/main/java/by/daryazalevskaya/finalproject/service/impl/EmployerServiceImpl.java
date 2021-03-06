package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.ContactDao;
import by.daryazalevskaya.finalproject.dao.CountryDao;
import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.EmployerService;

import java.util.Objects;
import java.util.Optional;


public class EmployerServiceImpl extends EmployerService {

    @Override
    public Integer createEmployer(Employer entity) throws DaoException, TransactionException {
        try {
            EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
            employerDao.create(entity);
            return entity.getId();
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Employer> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        Optional<Employer> employer = employerDao.read(id);
        if (employer.isPresent()) {
            if (Objects.nonNull(employer.get().getCountry().getId())) {
                CountryDao countryDao = transaction.createDao(DaoType.COUNTRY);
                Optional<Country> country = countryDao.read(employer.get().getCountry().getId());
                country.ifPresent(c -> employer.get().setCountry(c));
            }
            if (Objects.nonNull(employer.get().getContact())) {
                ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
                Optional<Contact> contact = contactDao.read(employer.get().getContact().getId());
                contact.ifPresent(c -> employer.get().setContact(c));
            }
        }
        return employer;
    }

    @Override
    public void update(Employer entity) throws DaoException, TransactionException {
        try {
            EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
            employerDao.update(entity);
            transaction.commit();
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
            Employer employer = employerDao.read(id).orElse(null);

            if (Objects.nonNull(employer)) {
                VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
                vacancyDao.deleteVacancyByEmployerId(employer.getId());


                ContactDao contactDao = transaction.createDao(DaoType.CONTACT);
                contactDao.delete(employer.getId());
            }
            employerDao.delete(id);

        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }


    @Override
    public void createUser(User user) throws DaoException, TransactionException {
        Employer employer = new Employer(user.getId());
        createEmployer(employer);
    }

    @Override
    public void deleteUser(Integer userId) throws PoolException, DaoException, TransactionException {
        delete(userId);
        transaction.commit();
    }

    @Override
    public void createContact(int employerId, Contact contact) throws DaoException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        employerDao.createContact(employerId, contact.getId());
    }

    @Override
    public boolean isRepeatedCompanyName(String company, Integer employerId) throws DaoException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        Integer employerIdByCompany=employerDao.readUserIdByCompany(company);
        boolean isRepeated=true;
        if (employerIdByCompany==null || employerIdByCompany.equals(employerId)) {
            isRepeated=false;
        }
        return isRepeated;
    }

}
