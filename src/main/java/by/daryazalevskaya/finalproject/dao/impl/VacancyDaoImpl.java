package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.dbcreator.VacancyCreator;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import by.daryazalevskaya.finalproject.service.sql.VacancyStatementFormer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacancyDaoImpl extends BaseDao implements VacancyDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM vacancy";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM vacancy WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO vacancy " +
            "(position, city, salary, currency, schedule, duties, requirements, employer_id)" +
            " VALUES (?, ?, ?, ?::currency_type, ?::schedule_type, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE vacancy SET  " +
            "position = ?, city=?, salary=?, currency=?::currency_type, " +
            "schedule=?::schedule_type, duties=?, requirements=?, employer_id=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM vacancy WHERE id =?";

    private static final String FIND_IN_RANGE = "SELECT * FROM vacancy LIMIT ? OFFSET ?";

    private static final String COUNT = "SELECT count(*) FROM vacancy";

    private static final String READ_BY_EMPLOYER_ID_QUERY = "SELECT city, " +
            "salary, schedule, duties, requirements,  position, currency, id FROM vacancy " +
            "WHERE employer_id=?";

    private static final String DELETE_EMPLOYEE_VACANCY="DELETE FROM employee_vacancies WHERE vacancy_id=?";

    private static final String DELETE_EMPLOYEE_VACANCIES_QUERY = "DELETE employee_vacancies WHERE employee_id=?";

    private static final String ADD_VACANCY_TO_EMPLOYEE_QUERY = "INSERT INTO employee_vacancies (vacancy_id, employee_id ) VALUES(?,?)";

    private static final String READ_VACANCIES_QUERY = "SELECT (vacancy_id) FROM employee_vacancies WHERE employee_id=?";

    @Override
    public Integer create(Vacancy entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new VacancyStatementFormer());
    }

    @Override
    public Optional<Vacancy> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new VacancyCreator());
    }

    @Override
    public void update(Vacancy entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Vacancy> statementFormer = new VacancyStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(9, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        delete(id, DELETE_QUERY);
    }

    @Override
    public List<Vacancy> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new VacancyCreator());
    }

    @Override
    public List<Vacancy> findFromTo(int start, int end) throws DaoException {
        return super.findInRange(FIND_IN_RANGE, new VacancyCreator(), start, end);
    }

    @Override
    public int count() throws DaoException {
        return super.count(COUNT);
    }

    @Override
    public List<Vacancy> findVacanciesByEmployerId(Integer id) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_EMPLOYER_ID_QUERY)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                VacancyCreator creator=new VacancyCreator();
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
    public void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException {
        delete(vacancyId, DELETE_EMPLOYEE_VACANCY);
    }

    @Override
    public void addEmployeeVacancy(int vacancyId, int employeeId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_VACANCY_TO_EMPLOYEE_QUERY)) {
            statement.setInt(1, vacancyId);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Vacancy> getEmployeeVacancies(int employeeId) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_VACANCIES_QUERY)) {
            statement.setInt(1, employeeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(new Vacancy(resultSet.getInt("vacancy_id")));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    @Override
    public void deleteEmployeeVacancies(int employeeId) throws DaoException {
        super.delete(employeeId, DELETE_EMPLOYEE_VACANCIES_QUERY);
    }
}
