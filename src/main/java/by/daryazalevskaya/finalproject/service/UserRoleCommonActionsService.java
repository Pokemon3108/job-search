package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.impl.EmployeeServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;

import java.util.EnumMap;
import java.util.Map;


public class UserRoleCommonActionsService {

    private  Map<Role, UserRoleService> map=new EnumMap<>(Role.class);

    public UserRoleCommonActionsService(Transaction transaction)  {
        EmployeeService employeeService=new EmployeeServiceImpl();
        employeeService.setTransaction(transaction);

        EmployerService employerService=new EmployerServiceImpl();
        employerService.setTransaction(transaction);

        map.put(Role.EMPLOYEE, employeeService);
        map.put(Role.EMPLOYER, employerService);
    }


    public void createAccount(User user) throws DaoException, InsertIdDataBaseException {
        UserRoleService service=map.get(user.getRole());
        service.createUser(user);
//        BaseService service=map.get(user.getRole());
//        service.addNewEntity(user);
    }


    public void deleteAccount(int userId, Role role) throws DaoException, PoolException {
        UserRoleService service=map.get(role);
        service.deleteUser(userId);
//        BaseService service=map.get(role);
//        service.de(userId);
    }


}
