package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Employee creator is used for creation {@code Employee} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employee.Employee}
 */
public class EmployeeCreator extends Creator<Employee> {
    @Override
    public Employee createEntity(ResultSet set) throws SQLException {
        return Employee.builder()
                .resume(new Resume(set.getInt("resume_id")))
                .id(set.getInt("user_id"))
                .build();
    }
}
