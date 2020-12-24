package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeInfoStatementFormer extends StatementFormer<EmployeePersonalInfo> {

    @Override
    public void fillStatement(PreparedStatement statement, EmployeePersonalInfo entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setDate(3, Date.valueOf(entity.getBirthday()));
        statement.setString(4, entity.getGender().toString());
        statement.setInt(5, entity.getId());
        statement.setString(6, entity.getCity());
    }
}
