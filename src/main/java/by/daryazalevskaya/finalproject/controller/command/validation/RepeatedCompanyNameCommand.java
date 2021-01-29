package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployerBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RepeatedCompanyNameCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) throws DaoException, TransactionException {
        EmployerBuilder builder = new EmployerBuilder();
        Employer employer = builder.build(request);
        boolean isValid=true;
        ServiceFactory serviceFactory = null;
        try {
            serviceFactory = new ServiceFactoryImpl();
            EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
            if (employerService.containsCompanyName(employer.getCompanyName(), employer.getId())) {
                isValid=false;
                request.setAttribute("repeatedCompany", true);
            }
            return isValid;
        } catch (ConnectionException ex) {
            throw new DaoException(ex);
        } finally {
            if (serviceFactory != null) {
                serviceFactory.close();
            }
        }

    }
}
