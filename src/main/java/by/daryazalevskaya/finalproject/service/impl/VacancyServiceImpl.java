package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.*;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class VacancyServiceImpl extends VacancyService {

    @Override
    public Integer createVacancy(Vacancy vacancy) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            Integer vacancyId = dao.create(vacancy);
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
                fillVacancy(vacancy.get());
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
    public List<Vacancy> readVacanciesByEmployerId(Integer id) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.readVacanciesByEmployerId(id);
        for (Vacancy vacancy : vacancies) {
            fillVacancy(vacancy);
        }
        return vacancies;
    }

    @Override
    public void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException, TransactionException {
        try {
            VacancyDao dao = transaction.createDao(DaoType.VACANCY);
            dao.deleteEmployeeVacanciesByVacancyId(vacancyId);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Vacancy> findAll() throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.findAll();
        for (Vacancy vacancy : vacancies) {
            fillVacancy(vacancy);
        }
        return vacancies;
    }

    @Override
    public List<Vacancy> findInRange(int start, int end) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = dao.findFromTo(start, end);
        for (Vacancy vacancy : vacancies) {
            fillVacancy(vacancy);
        }
        return vacancies;
    }

    @Override
    public int getVacanciesAmount() throws DaoException {
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
        List<Vacancy> vacancies = readEmployeeVacancies(employeeId);
        return vacancies.stream().anyMatch(vacancy1 -> vacancy1.getId().equals(vacancyId));
    }

    @Override
    public List<Vacancy> readEmployeeVacancies(Integer employeeId) throws DaoException {
        VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancies = vacancyDao.readEmployeeVacancies(employeeId);
        vacancies = vacancies.stream().map(vacancy -> {
            try {
                Vacancy vacancy1 = vacancyDao.read(vacancy.getId()).get();
                fillVacancy(vacancy1);
                return vacancy1;
            } catch (DaoException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return vacancies;
    }

    private void fillVacancy(Vacancy vacancy) throws DaoException {
        EmployerDao employerDao = transaction.createDao(DaoType.EMPLOYER);
        Optional<Employer> employer = employerDao.read(vacancy.getEmployer().getId());
        employer.ifPresent(vacancy::setEmployer);

        if (vacancy.getSpecialization() != null) {
            JobPreferenceDao preferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
            Optional<Specialization> specialization = preferenceDao.findSpecializationById(vacancy.getSpecialization().getId());
            specialization.ifPresent(vacancy::setSpecialization);
        }

        if (vacancy.getCountry() != null) {
            CountryDao countryDao = transaction.createDao(DaoType.COUNTRY);
            Optional<Country> country = countryDao.read(vacancy.getCountry().getId());
            country.ifPresent(vacancy::setCountry);
        }
    }

    @Override
    public List<Vacancy> readVacancyByParams(VacancySearchParams params, int limit, int offset) throws DaoException {
        VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);
        List<Vacancy> vacancyList = new ArrayList<>();
        if (!params.isEmptyCountry() && params.isEmptySpecialization() && params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesByCountryId
                    (params.getCountryId(), limit, offset);
        }
        if (!params.isEmptyCountry() && !params.isEmptySpecialization() && params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesBySpecializationIdAndCountryId
                    (params.getSpecializationId(), params.getCountryId(), limit, offset);
        }
        if (!params.isEmptyCountry() && !params.isEmptySpecialization() && !params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesBySpecializationIdAndCountryIdAndPosition
                    (params.getSpecializationId(), params.getCountryId(), params.getPosition(), limit, offset);
        }
        if (!params.isEmptyCountry() && params.isEmptySpecialization() && !params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesByPositionAndCountryId
                    (params.getPosition(), params.getCountryId(), limit, offset);
        }
        if (params.isEmptyCountry() && !params.isEmptySpecialization() && params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesBySpecializationId
                    (params.getSpecializationId(), limit, offset);
        }
        if (params.isEmptyCountry() && !params.isEmptySpecialization() && !params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesByPositionAndSpecializationId
                    (params.getPosition(), params.getSpecializationId(), limit, offset);
        }
        if (params.isEmptyCountry() && params.isEmptySpecialization() && !params.isEmptyPosition()) {
            vacancyList = vacancyDao.readVacanciesByPosition
                    (params.getPosition(), limit, offset);
        }

        for (Vacancy vacancy : vacancyList) {
            fillVacancy(vacancy);
        }

        return vacancyList;
    }

    @Override
    public Integer countVacanciesByParams(VacancySearchParams params) throws DaoException {
        VacancyDao vacancyDao = transaction.createDao(DaoType.VACANCY);

        if (!params.isEmptyCountry() && params.isEmptySpecialization() && params.isEmptyPosition()) {
            return vacancyDao.countVacanciesByCountryId(params.getCountryId());
        }
        if (!params.isEmptyCountry() && !params.isEmptySpecialization() && params.isEmptyPosition()) {
            return vacancyDao.countVacanciesBySpecializationIdAndCountryId(params.getSpecializationId(), params.getCountryId());
        }
        if (!params.isEmptyCountry() && !params.isEmptySpecialization() && !params.isEmptyPosition()) {
            return vacancyDao.countVacanciesBySpecializationIdAndCountryIdAndPosition
                    (params.getSpecializationId(), params.getCountryId(), params.getPosition());
        }
        if (!params.isEmptyCountry() && params.isEmptySpecialization() && !params.isEmptyPosition()) {
            return vacancyDao.countVacanciesByPositionAndCountryId(params.getPosition(), params.getCountryId());
        }
        if (params.isEmptyCountry() && !params.isEmptySpecialization() && params.isEmptyPosition()) {
            return vacancyDao.countVacanciesBySpecializationId(params.getSpecializationId());
        }
        if (params.isEmptyCountry() && !params.isEmptySpecialization() && !params.isEmptyPosition()) {
            return vacancyDao.countVacanciesByPositionAndSpecializationId(params.getPosition(), params.getSpecializationId());
        }
        if (params.isEmptyCountry() && params.isEmptySpecialization() && !params.isEmptyPosition()) {
            return vacancyDao.countVacanciesByPosition(params.getPosition());
        }
        return null;
    }

}
