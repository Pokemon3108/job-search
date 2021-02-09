package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.util.List;

/**
 * The interface Vacancy dao
 */
public interface VacancyDao extends Dao<Vacancy> {
    /**
     * Read vacancy list in range.
     *
     * @param start the start border
     * @param end   the end border
     * @return the list of vacancies
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readFromTo(int start, int end) throws DaoException;

    /**
     * Count amount of vacancies.
     *
     * @return vacancies amount
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    int count() throws DaoException;

    /**
     * Read vacancies by employer id.
     *
     * @param id employer id
     * @return the list of employer vacancies
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesByEmployerId(Integer id) throws DaoException;

    /**
     * Delete employee vacancy by vacancy id.
     *
     * @param vacancyId the vacancy id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void deleteEmployeeVacancyByVacancyId(Integer vacancyId) throws DaoException;

    /**
     * Delete employer vacancy by employer id.
     *
     * @param employerId the vacancy id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void deleteVacancyByEmployerId(Integer employerId) throws DaoException;

    /**
     * Add vacancy to employee vacancies.
     *
     * @param vacancyId  the vacancy id
     * @param employeeId the employee id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void addEmployeeVacancy(Integer vacancyId, Integer employeeId) throws DaoException;

    /**
     * Read vacancies, that employee responded on.
     *
     * @param employeeId the employee id
     * @return the list of employee vacancies
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readEmployeeVacancies(Integer employeeId) throws DaoException;

    /**
     * Delete all employee vacancies by employee id.
     *
     * @param employeeId the employee id
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    void deleteEmployeeVacanciesByEmployeeId(Integer employeeId) throws DaoException;

    /**
     * Read vacancies by specialization id.
     *
     * @param specializationId the specialization id
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesBySpecializationId(Integer specializationId, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by country id.
     *
     * @param countryId the country id
     * @param limit      amount of vacancies to be read
     * @param offset    the offset
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesByCountryId(Integer countryId, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by position.
     *
     * @param position of vacancy
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesByPosition(String position, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by specialization id and country id.
     *
     * @param specializationId the specialization id
     * @param countryId        the country id
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesBySpecializationIdAndCountryId(Integer specializationId, Integer countryId, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by position and country id.
     *
     * @param position  the position
     * @param countryId the country id
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesByPositionAndCountryId(String position, Integer countryId, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by position and specialization id.
     *
     * @param position         the position
     * @param specializationId the specialization id
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesByPositionAndSpecializationId(String position, Integer specializationId, int limit, int offset) throws DaoException;

    /**
     * Read vacancies by specialization id and country id and position list.
     *
     * @param specializationId the specialization id
     * @param countryId        the vacancy country id
     * @param position         the vacancy position
     * @param limit            amount of vacancies to be read
     * @param offset           the offset in table from which read will be started
     * @return the list
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    List<Vacancy> readVacanciesBySpecializationIdAndCountryIdAndPosition(Integer specializationId, Integer countryId, String position, int limit, int offset) throws DaoException;

    /**
     * Count amount of vacancies by specialization id.
     *
     * @param specializationId the specialization id
     * @return the amount of vacancies with chosen specialization
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesBySpecializationId(Integer specializationId) throws DaoException;

    /**
     * Count amount of vacancies by country id.
     *
     * @param countryId the country id
     * @return the amount of vacancies with chosen specialization
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesByCountryId(Integer countryId) throws DaoException;

    /**
     * Count amount of vacancies by position.
     *
     * @param position the position
     * @return the amount of vacancies with chosen position
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesByPosition(String position) throws DaoException;

    /**
     * Count amount of vacancies by specialization and country.
     *
     * @param specializationId the specialization id
     * @param countryId        the country id
     * @return the amount of vacancies with chosen specialization and country
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesBySpecializationIdAndCountryId(Integer specializationId, Integer countryId) throws DaoException;

    /**
     * Count amount of vacancies by position and country.
     *
     * @param position  the position
     * @param countryId the country id
     * @return the amount of vacancies with chosen position and country
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesByPositionAndCountryId(String position, Integer countryId) throws DaoException;

    /**
     * Count amount of vacancies by position and specialization.
     *
     * @param position         the position
     * @param specializationId the specialization id
     * @return the amount of vacancies with chosen position and specialization
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesByPositionAndSpecializationId(String position, Integer specializationId) throws DaoException;

    /**
     * Count amount of vacancies by specialization and country and position.
     *
     * @param specializationId the specialization id
     * @param countryId        the country id
     * @param position         the position
     * @return the amount of vacancies with chosen params
     * @throws DaoException the dao exception is thrown when occures error with access to database
     */
    Integer countVacanciesBySpecializationIdAndCountryIdAndPosition(Integer specializationId, Integer countryId, String position) throws DaoException;


}
