package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.dbcreator.EmployerCreator;
import by.daryazalevskaya.finalproject.service.sql.EmployerStatementFormer;
import by.daryazalevskaya.finalproject.service.sql.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployerDaoImpl extends BaseDao implements EmployerDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM employer";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM employer WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO employer " +
            "(company_name, country, city, contact_id, user_id) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE employer SET  " +
            "company_name = ?, country=?, city=?, contact_id=? WHERE user_id=?";


    private static final String DELETE_QUERY = "DELETE employer usr WHERE id =?";

    @Override
    public Integer create(Employer entity) throws DaoException {
        Integer id = null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            StatementFormer<Employer> former = new EmployerStatementFormer();
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
    public Optional<Employer> read(int id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new EmployerCreator());
    }

    @Override
    public void update(Employer entity) throws DaoException, IllegalOperationException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Employer> former = new EmployerStatementFormer();
            former.fillStatement(statement, entity);
            statement.setInt(5, entity.getId());
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
    public List<Employer> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new EmployerCreator());
    }
}
