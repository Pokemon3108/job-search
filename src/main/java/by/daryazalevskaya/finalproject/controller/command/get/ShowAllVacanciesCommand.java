package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;
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
            List<Vacancy> vacancies = vacancyService.findInRange(VACANCY_AMOUNT_ON_PAGE, (page - 1) * VACANCY_AMOUNT_ON_PAGE);
            request.setAttribute("vacancies", vacancies);
            int maxPage = vacancyService.getVacanciesSize();

            request.setAttribute("maxPage", maxPage / VACANCY_AMOUNT_ON_PAGE + 1);
            request.setAttribute("currentPage", request.getParameter("currentPage"));

            request.getRequestDispatcher(PagePath.ALL_VACANCIES).forward(request, response);
        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
