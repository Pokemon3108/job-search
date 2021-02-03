package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployerDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.dbcreator.EmployerCreator;
import by.daryazalevskaya.finalproject.service.statements.EmployerStatementFormer;
import by.daryazalevskaya.finalproject.service.statements.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * The type Employer dao for access to employee personal info table and linked with it
 */
public class EmployerDaoImpl extends BaseDao implements EmployerDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM employer";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM employer WHERE user_id=?";

    private static final String CREATE_QUERY = "INSERT INTO employer " +
            "(user_id) VALUES (?)";

    private static final String UPDATE_QUERY = "UPDATE employer SET  " +
            "company_name = ?, country=?, city=? WHERE user_id=?";


    private static final String DELETE_QUERY = "DELETE FROM employer WHERE user_id =?";

    private static final String CREATE_CONTACT = "UPDATE employer SET contact_id=? WHERE user_id=?";

    private static final String FIND_EMPLOYER_BY_COMPANY_NAME = "SELECT user_id FROM employer WHERE company_name =?";


    @Override
    public Integer create(Employer entity) throws DaoException {
        Integer id = null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, entity.getId());
            int row = statement.executeUpdate();

            if (row != 0) {
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
    public Optional<Employer> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new EmployerCreator());
    }

    @Override
    public void update(Employer entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<Employer> former = new EmployerStatementFormer();
            former.fillStatement(statement, entity);
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
    public List<Employer> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new EmployerCreator());
    }

    @Override
    public void createContact(Integer employerId, Integer contactId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_CONTACT)) {
            statement.setInt(1, contactId);
            statement.setInt(2, employerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer readUserIdByCompany(String company) throws DaoException {
        ResultSet set = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_EMPLOYER_BY_COMPANY_NAME)) {
            statement.setString(1, company);
            set = statement.executeQuery();
            Integer id = null;
            if (set.next()) {
                id = set.getInt(1);
            }

            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeSet(set);
        }
    }
}
