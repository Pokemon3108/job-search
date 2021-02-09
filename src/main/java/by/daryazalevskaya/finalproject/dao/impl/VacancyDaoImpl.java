package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.dbcreator.VacancyCreator;
import by.daryazalevskaya.finalproject.service.statements.SearchVacancyFormer;
import by.daryazalevskaya.finalproject.service.statements.StatementFormer;
import by.daryazalevskaya.finalproject.service.statements.VacancyStatementFormer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Vacancy dao is a dao for access to vacancy table and linked with it
 */
public class VacancyDaoImpl extends BaseDao implements VacancyDao {

    private static final String READ_ALL_QUERY = "SELECT city," +
            "salary, schedule, duties, requirements,  position, currency, id, country_id, specialization_id, employer_id FROM vacancy " +
            "ORDER BY id DESC";

    private static final String READ_BY_ID_QUERY = "SELECT city," +
            "salary, schedule, duties, requirements,  position, currency, id, country_id, specialization_id, employer_id FROM vacancy WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO vacancy " +
            "(position, city, salary, currency, schedule, duties, requirements, employer_id, country_id, specialization_id)" +
            " VALUES (?, ?, ?, ?::currency_type, ?::schedule_type, ?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE vacancy SET  " +
            "position = ?, city=?, salary=?, currency=?::currency_type, " +
            "schedule=?::schedule_type, duties=?, requirements=?, employer_id=?, country_id=?, specialization_id=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM vacancy WHERE id =?";

    private static final String DELETE_EMPLOYER_VACANCY = "DELETE FROM vacancy WHERE employer_id=?";

    private static final String FIND_IN_RANGE = "SELECT city," +
            "salary, schedule, duties, requirements,  position, currency, id, country_id, specialization_id, employer_id FROM vacancy ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT = "SELECT count(*) FROM vacancy";

    private static final String READ_BY_EMPLOYER_ID_QUERY = "SELECT city," +
            "salary, schedule, duties, requirements,  position, currency, id, country_id, specialization_id, employer_id FROM vacancy " +
            "WHERE employer_id=?";

    private static final String READ_VACANCIES_BY_SPECIALIZATION_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id FROM vacancy " +
            "WHERE specialization_id=? ORDER BY id DESC LIMIT ? OFFSET ? ";

    private static final String COUNT_BY_SPEC = "SELECT count(*) FROM vacancy WHERE specialization_id=?";

    private static final String READ_VACANCIES_BY_COUNTRY_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE country_id=? ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT_BY_COUNTRY = "SELECT count(*) FROM vacancy WHERE country_id=?";

    private static final String READ_VACANCIES_BY_POSITION = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE position like ? ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT_BY_POSITION = "SELECT count(*) FROM vacancy WHERE position like ?";

    private static final String READ_VACANCIES_BY_SPECIALIZATION_ID_AND_COUNTRY_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE specialization_id=? AND country_id=? ORDER BY id DESC LIMIT ? OFFSET ? ";

    private static final String COUNT_BY_SPEC_AND_COUNTRY = "SELECT count(*) FROM vacancy WHERE specialization_id=? AND country_id=?";

    private static final String READ_VACANCIES_POSITION_AND_BY_SPECIALIZATION_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE position like ? AND specialization_id=? ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT_BY_POSITION_AND_SPECIALIZATION = "SELECT count(*) FROM vacancy WHERE position like ? AND specialization_id=?";

    private static final String READ_VACANCIES_BY_POSITION_AND_COUNTRY_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE position like ? AND country_id=? ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT_BY_POSITION_AND_COUNTRY = "SELECT count(*) FROM vacancy WHERE position like ? AND country_id=?";

    private static final String READ_VACANCIES_BY_COUNTRY_ID_AND_POSITION_AND_SPECIALIZATION_ID = "SELECT city, " +
            "salary, position, currency, id, employer_id  FROM vacancy " +
            "WHERE country_id=? AND position like ? AND specialization_id=? ORDER BY id DESC LIMIT ? OFFSET ?";

    private static final String COUNT_BY_COUNTRY_AND_POSITION_AND_SPECIALIZATION
            = "SELECT count(*) FROM vacancy WHERE country_id=? AND position like ? AND specialization_id=?";

    private static final String DELETE_EMPLOYEE_VACANCY = "DELETE FROM employee_vacancies WHERE vacancy_id=?";

    private static final String DELETE_EMPLOYEE_VACANCIES_QUERY = "DELETE FROM employee_vacancies WHERE employee_id=?";

    private static final String ADD_VACANCY_TO_EMPLOYEE_QUERY = "INSERT INTO employee_vacancies (vacancy_id, employee_id ) VALUES(?,?)";

    private static final String READ_EMPLOYEE_VACANCIES_QUERY = "SELECT (vacancy_id) FROM employee_vacancies WHERE employee_id=?";


    @Override
    public Integer create(Vacancy entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new VacancyStatementFormer());
    }

    @Override
    public Optional<Vacancy> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new VacancyCreator());
    }

    @Override
    public void update(Vacancy entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Vacancy> statementFormer = new VacancyStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        delete(id, DELETE_QUERY);
    }

    @Override
    public List<Vacancy> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new VacancyCreator());
    }

    @Override
    public List<Vacancy> readFromTo(int start, int end) throws DaoException {
        return super.findInRange(FIND_IN_RANGE, new VacancyCreator(), start, end);
    }

    @Override
    public int count() throws DaoException {
        return super.count(COUNT);
    }

    @Override
    public List<Vacancy> readVacanciesByEmployerId(Integer id) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_EMPLOYER_ID_QUERY)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                VacancyCreator creator = new VacancyCreator();
                entities.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    @Override
    public void deleteEmployeeVacancyByVacancyId(Integer vacancyId) throws DaoException {
        delete(vacancyId, DELETE_EMPLOYEE_VACANCY);
    }

    @Override
    public void deleteVacancyByEmployerId(Integer employerId) throws DaoException {
        delete(employerId, DELETE_EMPLOYER_VACANCY);
    }

    @Override
    public void addEmployeeVacancy(Integer vacancyId, Integer employeeId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_VACANCY_TO_EMPLOYEE_QUERY)) {
            statement.setInt(1, vacancyId);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Vacancy> readEmployeeVacancies(Integer employeeId) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_EMPLOYEE_VACANCIES_QUERY)) {
            statement.setInt(1, employeeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(new Vacancy(resultSet.getInt("vacancy_id")));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    @Override
    public void deleteEmployeeVacanciesByEmployeeId(Integer employeeId) throws DaoException {
        super.delete(employeeId, DELETE_EMPLOYEE_VACANCIES_QUERY);
    }

    private List<Vacancy> readVacancyByParam(SearchVacancyFormer createStatement, final String query) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            createStatement.fillStatement(statement);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                VacancyCreator creator = new VacancyCreator();
                entities.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    @Override
    public List<Vacancy> readVacanciesBySpecializationId(Integer specializationId, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, specializationId);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_BY_SPECIALIZATION_ID);
    }

    @Override
    public List<Vacancy> readVacanciesByCountryId(Integer countryId, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, countryId);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_BY_COUNTRY_ID);
    }

    @Override
    public List<Vacancy> readVacanciesByPosition(String position, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setString(1, "%" + position + "%");
            statement.setInt(2, limit);
            statement.setInt(3, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_BY_POSITION);
    }

    @Override
    public List<Vacancy> readVacanciesBySpecializationIdAndCountryId
            (Integer specializationId, Integer countryId, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, specializationId);
            statement.setInt(2, countryId);
            statement.setInt(3, limit);
            statement.setInt(4, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_BY_SPECIALIZATION_ID_AND_COUNTRY_ID);
    }

    @Override
    public List<Vacancy> readVacanciesByPositionAndCountryId
            (String position, Integer countryId, int limit, int offset) throws DaoException {
        SearchVacancyFormer createStatement = (statement -> {
            statement.setString(1, "%" + position + "%");
            statement.setInt(2, countryId);
            statement.setInt(3, limit);
            statement.setInt(4, offset);
        });
        return readVacancyByParam(createStatement, READ_VACANCIES_BY_POSITION_AND_COUNTRY_ID);
    }

    @Override
    public List<Vacancy> readVacanciesByPositionAndSpecializationId
            (String position, Integer specializationId, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setString(1, "%" + position + "%");
            statement.setInt(2, specializationId);
            statement.setInt(3, limit);
            statement.setInt(4, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_POSITION_AND_BY_SPECIALIZATION_ID);
    }

    @Override
    public List<Vacancy> readVacanciesBySpecializationIdAndCountryIdAndPosition
            (Integer specializationId, Integer countryId, String position, int limit, int offset) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, countryId);
            statement.setString(2, "%" + position + "%");
            statement.setInt(3, specializationId);
            statement.setInt(4, limit);
            statement.setInt(5, offset);
        });
        return readVacancyByParam(former, READ_VACANCIES_BY_COUNTRY_ID_AND_POSITION_AND_SPECIALIZATION_ID);
    }

    private Integer countByParam(SearchVacancyFormer former,final String query ) throws DaoException {
        ResultSet resultSet = null;
        int amount=0;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            former.fillStatement(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(resultSet);
        }
        return amount;
    }

    @Override
    public Integer countVacanciesBySpecializationId(Integer specializationId) throws DaoException {
        SearchVacancyFormer former = (statement -> statement.setInt(1, specializationId));
        return countByParam(former, COUNT_BY_SPEC);
    }

    @Override
    public Integer countVacanciesByCountryId(Integer countryId) throws DaoException {
        SearchVacancyFormer former = (statement -> statement.setInt(1, countryId));
        return countByParam(former, COUNT_BY_COUNTRY);
    }

    @Override
    public Integer countVacanciesByPosition(String position) throws DaoException {
        SearchVacancyFormer former = (statement -> statement.setString(1, "%" + position + "%"));
        return countByParam(former, COUNT_BY_POSITION);
    }

    @Override
    public Integer countVacanciesBySpecializationIdAndCountryId(Integer specializationId, Integer countryId) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, specializationId);
            statement.setInt(2, countryId);
        });
        return countByParam(former, COUNT_BY_SPEC_AND_COUNTRY);
    }

    @Override
    public Integer countVacanciesByPositionAndCountryId(String position, Integer countryId) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setString(1, "%" + position + "%");
            statement.setInt(2, countryId);
        });
        return countByParam(former, COUNT_BY_POSITION_AND_COUNTRY);
    }

    @Override
    public Integer countVacanciesByPositionAndSpecializationId(String position, Integer specializationId) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setString(1, "%" + position + "%");
            statement.setInt(2, specializationId);
        });
        return countByParam(former, COUNT_BY_POSITION_AND_SPECIALIZATION);
    }

    @Override
    public Integer countVacanciesBySpecializationIdAndCountryIdAndPosition(Integer specializationId, Integer countryId, String position) throws DaoException {
        SearchVacancyFormer former = (statement -> {
            statement.setInt(1, countryId);
            statement.setString(2, "%" + position + "%");
            statement.setInt(3, specializationId);
        });
        return countByParam(former, COUNT_BY_COUNTRY_AND_POSITION_AND_SPECIALIZATION);
    }

}
