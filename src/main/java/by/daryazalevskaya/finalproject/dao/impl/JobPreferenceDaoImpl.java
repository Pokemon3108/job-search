package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;
import by.daryazalevskaya.finalproject.service.dbcreator.CountryCreator;
import by.daryazalevskaya.finalproject.service.dbcreator.JobPreferenceCreator;
import by.daryazalevskaya.finalproject.service.dbcreator.SpecializationCreator;
import by.daryazalevskaya.finalproject.service.sql.JobPreferenceStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log4j2
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
    public Optional<JobPreference> read(int id) throws DaoException {
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
    public void delete(int id) throws DaoException {
        delete(id, DELETE_QUERY);
    }

    @Override
    public List<JobPreference> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new JobPreferenceCreator());
    }

    @Override
    public Integer findIdBySpecialization(String specialization) throws DaoException {
        final String fieldName = "id";
        return findIdByField(specialization, FIND_SPEC_ID_QUERY, fieldName);
    }

    @Override
    public Optional<Specialization> findSpecializationById(int id) throws DaoException {
        final String name = "name";
        String countryName = findStringFieldById(id, FIND_SPEC_BY_ID_QUERY, name);
        Specialization specialization = new Specialization(id, countryName);
        return Optional.of(specialization);
    }

    @Override
    public List<Specialization> findAllSpecializations() throws DaoException {
        return super.findAll(FIND_ALL_SPEC_QUERY, new SpecializationCreator());
    }
}
