package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> findFromTo(int start, int end) throws DaoException;

    int count() throws DaoException;

    List<Vacancy> readVacanciesByEmployerId(Integer id) throws DaoException;

    void deleteEmployeeVacanciesByVacancyId(Integer vacancyId) throws DaoException;

    void addEmployeeVacancy(Integer vacancyId, Integer employeeId) throws DaoException;

    List<Vacancy> readEmployeeVacancies(Integer employeeId) throws DaoException;

    void deleteEmployeeVacanciesByEmployeeId(Integer employeeId) throws DaoException;

    List<Vacancy> readVacanciesBySpecializationId(Integer specializationId, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesByCountryId(Integer countryId, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesByPosition(String position, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesBySpecializationIdAndCountryId(Integer specializationId, Integer countryId, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesByPositionAndCountryId(String position, Integer countryId, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesByPositionAndSpecializationId(String position, Integer specializationId, int limit, int offset) throws DaoException;

    List<Vacancy> readVacanciesBySpecializationIdAndCountryIdAndPosition(Integer specializationId, Integer countryId, String position, int limit, int offset) throws DaoException;
}
