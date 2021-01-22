package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl extends VacancyService {
    @Override
    public List<Vacancy> findVacanciesByEmployerId(int id) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        return dao.findVacanciesByEmployerId(id);
    }

    @Override
    public void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            dao.deleteVacancyFromEmployeeVacancies(vacancyId);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Integer addNewVacancy(Vacancy vacancy) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            return dao.create(vacancy);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<Vacancy> read(Integer id) throws TransactionException, DaoException {
        if (id == null) {
            return Optional.empty();
        }
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            Optional<Vacancy> vacancy= dao.read(id);
            if (vacancy.isPresent()) {
                EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
                Optional<Employer> employer=employerDao.read(vacancy.get().getEmployer().getId());
                employer.ifPresent(employer1 -> vacancy.get().setEmployer(employer1));
            }
            return vacancy;
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void update(Vacancy vacancy) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            dao.update(vacancy);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        dao.delete(id);
    }

    @Override
    public List<Vacancy> findAll() throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.findAll();
        fillVacancies(vacancies);
        return vacancies;
    }

    @Override
    public List<Vacancy> findInRange(int start, int end) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies=dao.findFromTo(start, end);
        fillVacancies(vacancies);
        return vacancies;
    }

    private void fillVacancies(List<Vacancy> vacancies) throws DaoException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        for (Vacancy vacancy : vacancies) {
            vacancy.setEmployer(employerDao.read(vacancy.getEmployer().getId()).get());
        }
    }

    @Override
    public int getVacanciesSize() throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        return dao.count();
    }

}
