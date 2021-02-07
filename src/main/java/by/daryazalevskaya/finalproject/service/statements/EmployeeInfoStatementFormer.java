package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeInfoStatementFormer implements StatementFormer<EmployeePersonalInfo> {

    @Override
    public void fillStatement(PreparedStatement statement, EmployeePersonalInfo entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setDate(3, Date.valueOf(entity.getBirthday()));
        statement.setString(4, entity.getGender().toString());
        statement.setInt(5, entity.getCountry().getId());
        statement.setString(6, entity.getCity());
    }
}
