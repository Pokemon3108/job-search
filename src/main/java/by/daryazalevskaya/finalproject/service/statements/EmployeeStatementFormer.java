package by.daryazalevskaya.finalproject.service.statements;

import by.daryazalevskaya.finalproject.model.employee.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeStatementFormer implements StatementFormer<Employee> {
    @Override
    public void fillStatement(PreparedStatement statement, Employee entity) throws SQLException {
        statement.setInt(1, entity.getId());
    }
}
