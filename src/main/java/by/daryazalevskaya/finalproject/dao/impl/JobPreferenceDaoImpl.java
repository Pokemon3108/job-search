package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.service.dbcreator.JobPreferenceCreator;
import by.daryazalevskaya.finalproject.service.dbcreator.SpecializationCreator;
import by.daryazalevskaya.finalproject.service.statements.JobPreferenceStatementFormer;
import by.daryazalevskaya.finalproject.service.statements.StatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * The type Job preference dao is a dao for access to job preference table and specialization table
 */
public class JobPreferenceDaoImpl extends BaseDao implements JobPreferenceDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM job_preference";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM job_preference WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO job_preference " +
            "(position, salary, currency, specialization_id, schedule, experience)" +
            " VALUES (?, ?, ?::currency_type, ?, ?::schedule_type, ?)";

    private static final String FIND_SPEC_ID_QUERY = "SELECT id FROM specialization_type WHERE name=?";
    private static final String FIND_SPEC_BY_ID_QUERY = "SELECT * FROM specialization_type WHERE id=?";

    private static final String UPDATE_QUERY = "UPDATE job_preference SET  " +
            "position = ?, salary=?, currency=?::currency_type, " +
            "specialization_id=?, schedule=?::schedule_type, experience=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM job_preference WHERE id =?";

    private static final String FIND_ALL_SPEC_QUERY="SELECT id, name FROM specialization_type";


    @Override
    public Integer create(JobPreference entity) throws InsertIdDataBaseException, DaoException {
        StatementFormer<JobPreference> statementFormer=new JobPreferenceStatementFormer();
        return super.create(entity, CREATE_QUERY, statementFormer);
    }

    @Override
    public Optional<JobPreference> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new JobPreferenceCreator());
    }

    @Override
    public void update(JobPreference entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<JobPreference> statementFormer=new JobPreferenceStatementFormer();
            statementFormer.fillStatement(statement, entity);
            statement.setInt(7, entity.getId());
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
    public List<JobPreference> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new JobPreferenceCreator());
    }

    @Override
    public Integer readIdBySpecialization(String specialization) throws DaoException {
        final String fieldName = "id";
        return findIdByField(specialization, FIND_SPEC_ID_QUERY, fieldName);
    }

    @Override
    public Optional<Specialization> readSpecializationById(Integer id) throws DaoException {
        final String name = "name";
        String specName = super.findStringFieldById(id, FIND_SPEC_BY_ID_QUERY, name);
        if (specName.isEmpty()) {
            return Optional.empty();
        }
        Specialization specialization = new Specialization(id, specName);
        return Optional.of(specialization);
    }

    @Override
    public List<Specialization> readAllSpecializations() throws DaoException {
        return super.findAll(FIND_ALL_SPEC_QUERY, new SpecializationCreator());
    }
}
