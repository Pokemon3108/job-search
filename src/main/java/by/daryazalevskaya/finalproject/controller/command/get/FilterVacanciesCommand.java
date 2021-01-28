package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.requestbuilder.RequestBuilder;
import by.daryazalevskaya.finalproject.service.requestbuilder.VacancyParamsBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class FilterVacanciesCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestBuilder builder = new VacancyParamsBuilder();
        VacancySearchParams params = builder.build(request);

        if (params.isEmptyFull()) {
            ActionCommand command = new ShowAllVacanciesCommand();
            command.setServiceFactory(serviceFactory);
            command.execute(request, response);
        } else {
            VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);
            int page = 1;

            try {
                if (request.getParameter("currentPage") != null) {
                    page = Integer.parseInt(request.getParameter("currentPage"));
                }
            } catch (NumberFormatException ex) {
                page = 1;
            }

            try {
                final int VACANCY_AMOUNT_ON_PAGE = 2;
                List<Vacancy> vacancies = vacancyService.readVacancyByParams(params, VACANCY_AMOUNT_ON_PAGE, (page - 1) * VACANCY_AMOUNT_ON_PAGE);
                request.setAttribute("vacancies", vacancies);

                Integer maxPage = vacancyService.countVacanciesByParams(params);
                log.debug(maxPage);
                request.setAttribute("maxPage", Math.ceil(maxPage / VACANCY_AMOUNT_ON_PAGE));

                CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
                request.setAttribute("countries", new SortingService().sortCountriesByAlphabet(countryService.findAll()));

                JobPreferenceService preferenceService = (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);
                request.setAttribute("specializations", preferenceService.findAllSpecializations());

                request.setAttribute("vacancyParams", params);

                log.debug("Current page: ",request.getParameter("currentPage") );
                request.setAttribute("currentPage", request.getParameter("currentPage"));
                request.setAttribute("action", "filter");

                request.getRequestDispatcher(PagePath.ALL_VACANCIES).forward(request, response);
            } catch (DaoException ex) {
                log.error(ex);
                response.sendError(500);
            }

        }
    }
}
