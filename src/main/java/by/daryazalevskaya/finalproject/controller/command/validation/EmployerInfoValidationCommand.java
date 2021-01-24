package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployerBuilder;
import by.daryazalevskaya.finalproject.service.validator.EmployerInfoValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class EmployerInfoValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) throws DaoException, TransactionException {
        boolean isValid = true;
        EmployerBuilder builder = new EmployerBuilder();
        Employer employer = builder.build(request);

        EmployerInfoValidator validator = new EmployerInfoValidator();
        if (!validator.isValidCity(employer.getCity())) {
            isValid = false;
            request.setAttribute("invalidCity", true);
        }

        if (!validator.isValidCompanyName(employer.getCompanyName())) {
            isValid = false;
            request.setAttribute("invalidCompany", true);
        }

        ServiceFactory serviceFactory = null;
        try {
            serviceFactory = new ServiceFactoryImpl();
            EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
            if (employerService.containsCompanyName(employer.getCompanyName(), employer.getId())) {
                isValid = false;
                request.setAttribute("repeatedCompany", true);
            }
        } catch (ConnectionException ex) {
            throw new DaoException(ex);
        } finally {
            if (serviceFactory != null) {
                serviceFactory.close();
            }
        }

        if (!isValid) {
            request.setAttribute("employer", employer);
            log.info("Invalid employer info format with id: ", request.getSession().getAttribute("user"));
        }

        return isValid;

    }
}
