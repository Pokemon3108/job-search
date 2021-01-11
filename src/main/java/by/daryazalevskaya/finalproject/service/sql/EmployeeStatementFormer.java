package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.employee.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeStatementFormer extends StatementFormer<Employee> {
    @Override
    public void fillStatement(PreparedStatement statement, Employee entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }
}
