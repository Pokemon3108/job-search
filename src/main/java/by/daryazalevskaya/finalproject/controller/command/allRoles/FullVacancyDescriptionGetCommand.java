package by.daryazalevskaya.finalproject.controller.command.allRoles;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class FullVacancyDescriptionGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);
        try {
            Integer id = null;
            if (request.getParameter("id") != null) {
                id = Integer.parseInt(request.getParameter("id"));
                if (request.getSession(false) != null &&
                        request.getSession().getAttribute("user") != null &&
                        vacancyService.hasAlreadyRespond(id, (Integer) request.getSession().getAttribute("user"))) {

                    request.setAttribute("respond", true);
                }

            }
            Optional<Vacancy> vacancy = vacancyService.read(id);
            vacancy.ifPresent(vacancy1 -> request.setAttribute("vacancy", vacancy1));
            if (vacancy.isEmpty()) {
                response.sendError(500);
            }

            request.getServletContext().getRequestDispatcher(PagePath.VACANCY_SHOW).forward(request, response);
        } catch (NumberFormatException ex) {
            response.sendError(404);
        } catch (DaoException | TransactionException ex) {
            log.error(ex);
            response.sendError(500);
        }
    }
}
