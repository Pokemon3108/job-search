package by.daryazalevskaya.finalproject.controller.command.employer;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.utils.SortingService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class EmployerInfoGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        }
    }
}
