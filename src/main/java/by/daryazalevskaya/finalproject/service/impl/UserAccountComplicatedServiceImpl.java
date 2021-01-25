package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserAccountComplicatedService;
import by.daryazalevskaya.finalproject.service.UserRoleService;
import by.daryazalevskaya.finalproject.service.UserService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;

public class UserAccountComplicatedServiceImpl extends UserAccountComplicatedService {
    @Override
    public boolean isRegistered(User user) throws DaoException, InsertIdDataBaseException, TransactionException {
        UserService service = new UserServiceImpl();
        service.setTransaction(transaction);
        try {
            Integer userId = service.createUser(user);
            if (userId != null) {
                ServiceFactory serviceFactory = new ServiceFactoryImpl();
                UserRoleService roleService = serviceFactory.createService(user.getRole());
                roleService.setTransaction(transaction);
                roleService.createUser(user);
            }
            transaction.commit();
            return userId!=null;
        } catch (ConnectionException ex) {
            throw new DaoException(ex);
        }

    }
}
