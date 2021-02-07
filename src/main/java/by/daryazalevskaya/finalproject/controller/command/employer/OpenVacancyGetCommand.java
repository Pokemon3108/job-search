package by.daryazalevskaya.finalproject.controller.command.employer;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.utils.SortingService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class OpenVacancyGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
        try {
            Optional<Employer> employer = employerService.read(userId);

            if (employer.isPresent() && employer.get().getCompanyName()!=null) {
                JobPreferenceService jobPreferenceService = (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);
                request.setAttribute("specializations", jobPreferenceService.findAllSpecializations());

                CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
                SortingService sortingService = new SortingService();
                request.setAttribute("countries", sortingService.sortCountriesByAlphabet(countryService.findAll()));

                request.setAttribute("schedules", Schedule.values());
                request.setAttribute("currencies", Currency.values());
                request.setAttribute("action", "open");
                request.getServletContext()
                        .getRequestDispatcher(PagePath.VACANCY_EDIT)
                        .forward(request, response);

            } else {
                request.getServletContext()
                        .getRequestDispatcher(PagePath.VACANCY_ERROR)
                        .forward(request, response);
            }
        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
