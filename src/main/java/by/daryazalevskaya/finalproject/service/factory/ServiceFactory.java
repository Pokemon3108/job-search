package by.daryazalevskaya.finalproject.service.factory;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.BaseService;
import by.daryazalevskaya.finalproject.service.UserRoleService;

public interface ServiceFactory {
    BaseService createService(DaoType daoType);

    UserRoleService createService(Role role);

    void close() throws TransactionException;

}
