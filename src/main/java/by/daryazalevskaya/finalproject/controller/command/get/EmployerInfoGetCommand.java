package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.CountryServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class EmployerInfoGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
            SortingService sortingService = new SortingService();
            request.setAttribute("countries", sortingService.sortCountriesByAlphabet(countryService.findAll()));

            Integer userId = (Integer) request.getSession().getAttribute("user");
            EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
            Optional<Employer> employer = employerService.read(userId);
            employer.ifPresent(employer1 -> request.setAttribute("employer", employer.get()));

            request.getServletContext()
                    .getRequestDispatcher(PagePath.EMPLOYER_INFO)
                    .forward(request, response);
        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }

    }
}
