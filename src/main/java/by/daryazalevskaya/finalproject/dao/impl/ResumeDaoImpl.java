package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.ResumeDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.creator.ResumeCreator;
import by.daryazalevskaya.finalproject.service.sql.ResumeStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResumeDaoImpl extends BaseDao implements ResumeDao {


    private static final String READ_ALL_QUERY = "SELECT * FROM resume";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM resume WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO resume " +
            "(prof_description, usr_id, contact_id, personal_info_id, job_preference_id) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE resume SET  " +
            "prof_description = ?,  usr_id = ?, contact_id = ?, personal_info_id = ?, job_preference_id=?" +
            " WHERE id=?";

    private static final String DELETE_QUERY = "DELETE resume WHERE id =?";

    @Override
    public Integer create(Resume entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new ResumeStatementFormer());
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
}
