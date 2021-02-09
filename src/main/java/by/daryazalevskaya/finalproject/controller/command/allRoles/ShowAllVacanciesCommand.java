package by.daryazalevskaya.finalproject.controller.command.allRoles;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.utils.SortingService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class ShowAllVacanciesCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);
            int page = 1;
            try {
                if (request.getParameter("currentPage") != null) {
                    page = Integer.parseInt(request.getParameter("currentPage"));
                }
            } catch (NumberFormatException ex) {
                page = 1;
            }

            final int VACANCY_AMOUNT_ON_PAGE = 2;
            int vacanciesAmount = vacancyService.getVacanciesAmount();
            int maxPage=(int) Math.ceil ((float)vacanciesAmount / VACANCY_AMOUNT_ON_PAGE);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("action", "show");

            page= Math.min(maxPage, page);
            request.setAttribute("currentPage", page);

            List<Vacancy> vacancies = vacancyService.findInRange(VACANCY_AMOUNT_ON_PAGE,  (page - 1) * VACANCY_AMOUNT_ON_PAGE);
            request.setAttribute("vacancies", vacancies);

            CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
            request.setAttribute("countries", new SortingService().sortCountriesByAlphabet(countryService.findAll()));

            JobPreferenceService preferenceService= (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);
            request.setAttribute("specializations", preferenceService.findAllSpecializations());


            request.getRequestDispatcher(PagePath.ALL_VACANCIES).forward(request, response);
        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
