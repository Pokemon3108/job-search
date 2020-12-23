package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.service.creator.JobPreferenceCreator;
import by.daryazalevskaya.finalproject.service.sql.JobPreferenceStatementFormer;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class JobPreferenceDaoImpl extends ConnectionDao implements JobPreferenceDao, DefaultOperationsDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM job_preference";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM job_preference WHERE id=?";
    private static final String CREATE_QUERY = "INSERT INTO job_preference " +
            "(desired_position, salary, currency, specialization_id, schedule, experience)" +
            " VALUES (?, ?, ?::currency_type, ?, ?::schedule_type, ?)";

    private static final String FIND_SPEC_ID_QUERY = "SELECT id FROM specialization_type WHERE name=?";
    private static final String FIND_SPEC_BY_ID_QUERY="SELECT * FROM specialization_type WHERE id=?";

    private static final String UPDATE_QUERY = "UPDATE job_preference SET  " +
            "desired_position = ?, salary=?, currency=?::currency_type, " +
            "specialization_id=?, schedule=?::schedule_type, experience=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM usr WHERE id =?";

    @Override
    public Integer create(JobPreference entity) throws InsertIdDataBaseException, DaoException {
        ResultSet resultSet = null;
        Integer id;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            JobPreferenceStatementFormer former=new JobPreferenceStatementFormer
                    (this.findIdBySpecialization(entity.getSpecialization()));
            former.setStatement(statement, entity);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                throw new InsertIdDataBaseException("There is no auto incremented index after trying to add record into table usr");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }
        return id;
    }

    @Override
    public Optional<JobPreference> read(int id) throws DaoException {
        ResultSet resultSet = null;
        JobPreference jobPreference = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_ID_QUERY)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JobPreferenceCreator creator = new JobPreferenceCreator(arg-> {
                    try {
                        return findSpecializationById(arg);
                    } catch (DaoException e) {
                        log.error(e);
                    }
                    return null;
                });

                jobPreference=creator.createEntity(resultSet);

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return Optional.ofNullable(jobPreference);
    }

    @Override
    public void update(JobPreference entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            JobPreferenceStatementFormer former=new JobPreferenceStatementFormer
                    (this.findIdBySpecialization(entity.getSpecialization()));
            former.setStatement(statement, entity);
            statement.setInt(7, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        delete(id, connection, DELETE_QUERY);
    }

    @Override
    public List<JobPreference> findAll() throws DaoException {
        ResultSet resultSet = null;

        List<JobPreference> preferences = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL_QUERY)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                JobPreferenceCreator creator = new JobPreferenceCreator(arg-> {
                    try {
                        return findSpecializationById(arg);
                    } catch (DaoException e) {
                        log.error(e);
                    }
                    return null;
                });

                preferences.add(creator.createEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return preferences;
    }

    @Override
    public Integer findIdBySpecialization(String specialization) throws DaoException {
        ResultSet resultSet = null;
        Integer id=null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_SPEC_ID_QUERY)) {
            statement.setString(1, specialization);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id=resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                log.error(e);
            }
        }

        return id;

    }

    @Override
    public String findSpecializationById(int id) throws DaoException {
        final String fieldName="name";
        return findFieldById(id, connection, FIND_SPEC_BY_ID_QUERY, fieldName);
    }
}
