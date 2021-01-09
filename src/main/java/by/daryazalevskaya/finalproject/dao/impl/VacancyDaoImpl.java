package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.dbcreator.VacancyCreator;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import by.daryazalevskaya.finalproject.service.sql.VacancyStatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VacancyDaoImpl extends BaseDao implements VacancyDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM vacancy";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM vacancy WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO vacancy " +
            "(position, city, min_experience, schedule, duties, requirements, employer_id)" +
            " VALUES (?, ?, ?, ?::schedule_type, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE vacancy SET  " +
            "position = ?, city=?, min_experience=?, " +
            "schedule=?::schedule_type, duties=?, requirements=?, employer_id=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE vacancy WHERE id =?";

    private static final String FIND_IN_RANGE="SELECT * FROM vacancy LIMIT ?,?";

    private static final String COUNT="SELECT count(*) FROM vacancy";

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
            statement.setInt(8, entity.getId());
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
}
