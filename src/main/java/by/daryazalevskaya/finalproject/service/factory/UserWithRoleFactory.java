package by.daryazalevskaya.finalproject.service.factory;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployeeService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.impl.EmployeeServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;

@SuppressWarnings("unchecked")
public class UserWithRoleFactory {

    public void createUser(User user, Transaction transaction) throws InsertIdDataBaseException, DaoException {
        switch (user.getRole()) {
            case EMPLOYEE:
                EmployeeService employeeService = new EmployeeServiceImpl();
                employeeService.setTransaction(transaction);
                Employee employee = new Employee(user.getId());
                employeeService.addNewEntity(employee);
                break;
            case EMPLOYER:
                EmployerService employerService = new EmployerServiceImpl();
                employerService.setTransaction(transaction);
                Employer employer = new Employer(user.getId());
                employerService.addNewEntity(employer);
                break;
        }

    }
}
