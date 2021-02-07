package by.daryazalevskaya.finalproject.service.factory;

import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.BaseService;
import by.daryazalevskaya.finalproject.service.UserRoleService;

/**
 * The interface Service factory defines operations with creation services
 */
public interface ServiceFactory {
    /**
     * Create  base service.
     * {@link by.daryazalevskaya.finalproject.service.BaseService}
     *
     * @param daoType the type of service to be created
     * @return the base service
     */
    BaseService createService(DaoType daoType);

    /**
     * Create  user role service.
     * {@link by.daryazalevskaya.finalproject.service.UserRoleService}
     *
     * @param role the role of user
     * @return the service
     */
    UserRoleService createService(Role role);

    /**
     * Close service factory
     *
     * @throws TransactionException if occures errors with transaction
     */
    void close() throws TransactionException;

}
