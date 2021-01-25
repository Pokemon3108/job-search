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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class VacancyServiceImpl extends VacancyService {

    @Override
    public Integer createVacancy(Vacancy vacancy) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            Integer vacancyId= dao.create(vacancy);
            transaction.commit();
            return vacancyId;
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
            Optional<Vacancy> vacancy = dao.read(id);
            if (vacancy.isPresent()) {
                EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
                Optional<Employer> employer = employerDao.read(vacancy.get().getEmployer().getId());
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
            transaction.commit();
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }

    }

    @Override
    public void delete(Integer id) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        dao.delete(id);
    }

    @Override
    public List<Vacancy> findVacanciesByEmployerId(Integer id) throws DaoException {
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
    public List<Vacancy> findAll() throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.findAll();
        fillVacancies(vacancies);
        return vacancies;
    }

    @Override
    public List<Vacancy> findInRange(int start, int end) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.findFromTo(start, end);
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

    @Override
    public void addEmployeeVacancy(Integer vacancyId, Integer employeeId) throws DaoException, TransactionException {
        VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
        if (!hasAlreadyRespond(vacancyId, employeeId)) {
            try {
                vacancyDao.addEmployeeVacancy(vacancyId, employeeId);
                transaction.commit();
            } catch (DaoException ex) {
                transaction.rollback();
                throw new DaoException(ex);
            }
        }
    }

    @Override
    public boolean hasAlreadyRespond(Integer vacancyId, Integer employeeId) throws DaoException {
        List<Vacancy> vacancies = findEmployeeVacancies(employeeId);
        return vacancies.stream().anyMatch(vacancy1 -> vacancy1.getId().equals(vacancyId));
    }

    @Override
    public List<Vacancy> findEmployeeVacancies(Integer employeeId) throws DaoException {
        VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies= vacancyDao.getEmployeeVacancies(employeeId);
        vacancies=vacancies.stream().map(vacancy -> {
            try {
                return vacancyDao.read(vacancy.getId()).get();
            } catch (DaoException e) {
               return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        fillVacancies(vacancies);
        return vacancies;
    }

}
