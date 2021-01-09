package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployeeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.dbcreator.EmployeeCreator;
import by.daryazalevskaya.finalproject.service.sql.EmployeeStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM employee";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM employee WHERE user_id=?";

    private static final String CREATE_QUERY = "INSERT INTO employee " +
            "(resume_id, user_id) VALUES (?, ?)";

    private static final String UPDATE_QUERY = "UPDATE employee SET  " +
            "resume_id = ?  WHERE user_id=?";

    private static final String DELETE_QUERY = "DELETE employee WHERE user_id =?";

    private static final String READ_VACANCIES_QUERY="SELECT * FROM employee_vacancies WHERE employee_id=?";

    private static final String FIND_IN_RANGE="SELECT * FROM employee LIMIT ?,?";

    private static final String COUNT="SELECT count(*) FROM employee";


    @Override
    public Integer create(Employee entity) throws  DaoException {
        Integer id = null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            StatementFormer<Employee> former = new EmployeeStatementFormer();
            former.fillStatement(statement, entity);
            int row=statement.executeUpdate();

            if (row!=0) {
                id = entity.getId();
            } else {
                throw new DaoException("Can't create entity 'employer'");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    @Override
    public Optional<Employee> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new EmployeeCreator());
    }

    @Override
    public void update(Employee entity) throws DaoException, IllegalOperationException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Employee> former = new EmployeeStatementFormer();
            former.fillStatement(statement, entity);
            statement.setInt(2, entity.getId());
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
    public List<Employee> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new EmployeeCreator());
    }

    @Override
    public List<Vacancy> getEmployeeVacancies(int employeeId) throws DaoException {
        ResultSet resultSet = null;

        List<Vacancy> entities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_VACANCIES_QUERY)) {
            statement.setInt(1, employeeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(new Vacancy(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }

        return entities;
    }

    @Override
    public List<Employee> findFromTo(int start, int end) throws DaoException {
        return super.findInRange(FIND_IN_RANGE, new EmployeeCreator(), start, end);
    }

    @Override
    public int count() throws DaoException {
        return super.count(COUNT);
    }


}
