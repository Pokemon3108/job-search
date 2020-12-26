package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCreator extends Creator<Employee> {
    @Override
    public Employee createEntity(ResultSet set) throws SQLException {
        return Employee.builder()
                .resume(new Resume(set.getInt("resume_id")))
                .user(new User(set.getInt("user_id")))
                .build();
    }
}
