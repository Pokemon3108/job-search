package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.ResumeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.dbcreator.ResumeCreator;
import by.daryazalevskaya.finalproject.service.sql.ResumeStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResumeDaoImpl extends BaseDao implements ResumeDao {


    private static final String READ_ALL_QUERY = "SELECT * FROM resume";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM resume WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO resume " +
            "(usr_id) VALUES (?)";

    private static final String UPDATE_QUERY = "UPDATE resume SET  " +
            "prof_description = ?,  usr_id = ?, contact_id = ?, personal_info_id = ?, job_preference_id=?" +
            " WHERE id=?";

    private static final String DELETE_QUERY = "DELETE  FROM resume WHERE id =?";

    private static final String READ_LANGUAGES_QUERY="SELECT * FROM resume_languages WHERE resume_id=?";

    private static final String DELETE_LANGUAGES_QUERY="DELETE FROM resume_languages WHERE resume_id=?";

    private static final String CREATE_CONTACT="UPDATE resume SET contact_id=? WHERE id=?";

    private static final String CREATE_PERSONAL_INFO="UPDATE resume SET personal_info_id=? WHERE id=?";

    private static final String CREATE_SKILLS="UPDATE resume SET prof_description=? WHERE id=?";

    private static final String CREATE_JOB_PREFERENCE="UPDATE resume SET job_preference_id=? WHERE id=?";

    //private static final String CREATE_LANGUAGES="INSERT INTO resume_languages (resume_id) VALUES (?)";

    @Override
    public Integer create(Resume entity) throws InsertIdDataBaseException, DaoException {
        ResultSet resultSet = null;
        Integer id = null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getUser().getId());
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
            closeSet(resultSet);
        }
        return id;
    }

    @Override
    public Optional<Resume> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new ResumeCreator());
    }

    @Override
    public void update(Resume entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Resume> former = new ResumeStatementFormer();
            former.fillStatement(statement, entity);
            statement.setInt(6, entity.getId());
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
    public List<Resume> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new ResumeCreator());
    }

    @Override
    public List<Language> getResumeLanguages(int resumeId) throws DaoException {
        ResultSet resultSet = null;

        List<Language> languages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(READ_LANGUAGES_QUERY)) {
            statement.setInt(1, resumeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                languages.add(new Language(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            closeSet(resultSet);
        }

        return languages;
    }

    @Override
    public void deleteResumeLanguage(int resumeId) throws DaoException {
        delete(resumeId, DELETE_LANGUAGES_QUERY);
    }

    @Override
    public void createContact(Resume resume) throws DaoException {
        createField(resume.getContact().getId(), resume.getId(), CREATE_CONTACT);
    }

    @Override
    public void createPersonalInfo(Resume resume) throws DaoException {
        createField(resume.getPersonalInfo().getId(), resume.getId(), CREATE_PERSONAL_INFO);
    }

    @Override
    public void updateSkills(Resume resume) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_SKILLS)) {
            statement.setString(1, resume.getSkills());
            statement.setInt(2, resume.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createJobPreference(Resume resume) throws DaoException {
        createField(resume.getJobPreference().getId(), resume.getId(), CREATE_JOB_PREFERENCE);
    }

    @Override
    public void createLanguages(Resume resume) throws DaoException {

    }

    private void createField(int fieldId, int resumeId,  String query) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, fieldId);
            statement.setInt(2, resumeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
