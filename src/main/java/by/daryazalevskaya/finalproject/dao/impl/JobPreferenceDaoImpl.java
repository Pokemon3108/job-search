package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.service.dbcreator.Creator;
import by.daryazalevskaya.finalproject.service.dbcreator.JobPreferenceCreator;
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
            "(desired_position, salary, currency, specialization_id, schedule, experience)" +
            " VALUES (?, ?, ?::currency_type, ?, ?::schedule_type, ?)";

    private static final String FIND_SPEC_ID_QUERY = "SELECT id FROM specialization_type WHERE name=?";
    private static final String FIND_SPEC_BY_ID_QUERY = "SELECT * FROM specialization_type WHERE id=?";

    private static final String UPDATE_QUERY = "UPDATE job_preference SET  " +
            "desired_position = ?, salary=?, currency=?::currency_type, " +
            "specialization_id=?, schedule=?::schedule_type, experience=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM usr WHERE id =?";

    @Override
    public Integer create(JobPreference entity) throws InsertIdDataBaseException, DaoException {

        JobPreferenceStatementFormer former = new JobPreferenceStatementFormer
                (this.findIdBySpecialization(entity.getSpecialization()));
        return super.create(entity, CREATE_QUERY, former);
    }

    @Override
    public Optional<JobPreference> read(int id) throws DaoException {
        Creator<JobPreference> creator = new JobPreferenceCreator(arg -> {
            try {
                return findSpecializationById(arg);
            } catch (DaoException e) {
                log.error(e);
            }
            return null;
        });

        return super.readById(id, READ_BY_ID_QUERY, creator);
    }

    @Override
    public void update(JobPreference entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<JobPreference> former = new JobPreferenceStatementFormer
                    (this.findIdBySpecialization(entity.getSpecialization()));
            former.fillStatement(statement, entity);
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
        Creator<JobPreference> creator = new JobPreferenceCreator(arg -> {
                    try {
                        return findSpecializationById(arg);
                    } catch (DaoException e) {
                        log.error(e);
                    }
                    return null;
                });
        return super.findAll(READ_ALL_QUERY, creator);
    }

    @Override
    public Integer findIdBySpecialization(String specialization) throws DaoException {
        final String fieldName = "id";
        return findIdByField(specialization, FIND_SPEC_ID_QUERY, fieldName);
    }

    @Override
    public String findSpecializationById(int id) throws DaoException {
        final String fieldName = "name";
        return findStringFieldById(id, FIND_SPEC_BY_ID_QUERY, fieldName);
    }
}
