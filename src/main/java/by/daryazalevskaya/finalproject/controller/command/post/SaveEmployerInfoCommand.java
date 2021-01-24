package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.EmployerInfoValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployerBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveEmployerInfoCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            ValidationCommand validationCommand = new EmployerInfoValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
                request.setAttribute("countries", new SortingService().sortCountriesByAlphabet(countryService.findAll()));
                request.getServletContext().getRequestDispatcher(PagePath.EMPLOYER_INFO).forward(request, response);
            } else {
                EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
                EmployerBuilder builder = new EmployerBuilder();
                Employer employer = builder.build(request);

                employerService.update(employer);

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_HOME.getUrl());
            }

        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
