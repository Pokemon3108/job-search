package by.daryazalevskaya.finalproject.service.factory;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Role;
import by.daryazalevskaya.finalproject.service.BaseService;
import by.daryazalevskaya.finalproject.service.UserRoleService;
import by.daryazalevskaya.finalproject.service.impl.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * The class {@code ServiceFactoryImpl} creates services of chosen type
 */
public class ServiceFactoryImpl implements ServiceFactory {

    private static Map<DaoType, BaseService> serviceMap = new EnumMap<>(DaoType.class);
    private static Map<Role, UserRoleService> userRoleServiceMap = new EnumMap<>(Role.class);

    static {
        serviceMap.put(DaoType.CONTACT, new ContactServiceImpl());
        serviceMap.put(DaoType.COUNTRY, new CountryServiceImpl());
        serviceMap.put(DaoType.EMPLOYEE, new EmployeeServiceImpl());
        serviceMap.put(DaoType.EMPLOYEE_PERSONAL_INFO, new EmployeePersonalInfoServiceImpl());
        serviceMap.put(DaoType.EMPLOYER, new EmployerServiceImpl());
        serviceMap.put(DaoType.JOB_PREFERENCE, new JobPreferenceServiceImpl());
        serviceMap.put(DaoType.RESUME, new ResumeServiceImpl());
        serviceMap.put(DaoType.USER, new UserServiceImpl());
        serviceMap.put(DaoType.VACANCY, new VacancyServiceImpl());
        serviceMap.put(DaoType.EMPLOYEE_LANGUAGE, new EmployeeLanguageServiceImpl());
        serviceMap.put(DaoType.COMPLICATED_RESUME, new ResumeComplicatedServiceImpl());
        serviceMap.put(DaoType.COMPLICATED_EMPLOYER, new EmployerComplicatedServiceImpl());
        serviceMap.put(DaoType.COMPLICATED_VACANCY, new VacancyComplicatedServiceImpl());
        serviceMap.put(DaoType.USER_ACCOUNT, new UserAccountComplicatedServiceImpl());

        userRoleServiceMap.put(Role.EMPLOYEE, new EmployeeServiceImpl());
        userRoleServiceMap.put(Role.EMPLOYER, new EmployerServiceImpl());
    }

    private TransactionFactory transactionFactory;

    /**
     * Instantiates a new Service factory.
     *
     * @param factory of transactions
     * {@link by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory}
     */
    public ServiceFactoryImpl(TransactionFactory factory) {
        this.transactionFactory = factory;
    }

    /**
     * Instantiates a new Service factory.
     *
     * @throws ConnectionException if occcures errors with connection to databaseh
     */
    public ServiceFactoryImpl() throws ConnectionException {
        this.transactionFactory=new TransactionFactoryImpl();
    }

    @Override
    public BaseService createService(DaoType daoType) {
        BaseService service = serviceMap.get(daoType);
        service.setTransaction(transactionFactory.createTransaction());
        return service;
    }

    @Override
    public UserRoleService createService(Role role) {
        UserRoleService roleService = userRoleServiceMap.get(role);
        roleService.setTransaction(transactionFactory.createTransaction());
        return roleService;
    }

    @Override
    public void close() throws TransactionException {
        transactionFactory.close();
    }
}
