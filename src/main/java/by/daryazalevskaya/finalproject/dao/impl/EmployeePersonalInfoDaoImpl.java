package by.daryazalevskaya.finalproject.dao.impl;

import by.daryazalevskaya.finalproject.dao.EmployeePersonalInfoDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.service.dbcreator.EmployeePersonalInfoCreator;
import by.daryazalevskaya.finalproject.service.statements.EmployeeInfoStatementFormer;
import by.daryazalevskaya.finalproject.service.statements.StatementFormer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeePersonalInfoDaoImpl extends BaseDao implements EmployeePersonalInfoDao {

    private static final String READ_ALL_QUERY = "SELECT * FROM employee_personal_info";

    private static final String READ_BY_ID_QUERY = "SELECT * FROM employee_personal_info WHERE id=?";

    private static final String CREATE_QUERY = "INSERT INTO employee_personal_info " +
            "(name, surname, birthday, gender, country, city)" +
            " VALUES (?, ?, ?, ?::gender_type, ?, ?)";

    private static final String UPDATE_QUERY = "UPDATE employee_personal_info SET  " +
            "name = ?, surname=?, birthday=?, " +
            "gender=?::gender_type, country=?, city=? WHERE id=?";

    private static final String DELETE_QUERY = "DELETE FROM employee_personal_info usr WHERE id =?";

    @Override
    public Integer create(EmployeePersonalInfo entity) throws InsertIdDataBaseException, DaoException {
        return super.create(entity, CREATE_QUERY, new EmployeeInfoStatementFormer());
    }

    @Override
    public Optional<EmployeePersonalInfo> read(Integer id) throws DaoException {
        return super.readById(id, READ_BY_ID_QUERY, new EmployeePersonalInfoCreator());
    }

    @Override
    public void update(EmployeePersonalInfo entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            StatementFormer<EmployeePersonalInfo> former=new EmployeeInfoStatementFormer();
            former.fillStatement(statement, entity);
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
    public List<EmployeePersonalInfo> findAll() throws DaoException {
        return super.findAll(READ_ALL_QUERY, new EmployeePersonalInfoCreator());
    }


}
