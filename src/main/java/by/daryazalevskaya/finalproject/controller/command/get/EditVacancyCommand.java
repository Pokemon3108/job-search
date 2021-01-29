package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.VacancyService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class EditVacancyCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer vacancyId = Integer.parseInt(request.getParameter("vacancyId"));
        VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);
        try {
            Optional<Vacancy> vacancy = vacancyService.read(vacancyId);
            if (vacancy.isPresent()) {
                JobPreferenceService jobPreferenceService = (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);
                request.setAttribute("specializations", jobPreferenceService.findAllSpecializations());

                CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
                request.setAttribute("countries", new SortingService().sortCountriesByAlphabet(countryService.findAll()));

                request.setAttribute("schedules", Schedule.values());
                request.setAttribute("currencies", Currency.values());
                request.setAttribute("action", "edit");
                request.setAttribute("vacancy", vacancy.get());
                request.getServletContext()
                        .getRequestDispatcher(PagePath.VACANCY_EDIT)
                        .forward(request, response);
            } else {
                response.sendError(404);
            }
        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
