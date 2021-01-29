package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.service.VacancyComplicatedService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class DeleteEmployeeVacancy extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        VacancyComplicatedService service= (VacancyComplicatedService) serviceFactory.createService(DaoType.COMPLICATED_VACANCY);
        Integer vacancyId = Integer.parseInt(request.getParameter("vacancyId"));
        try {
            service.deleteVacancy(vacancyId);
            response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_VACANCIES.getUrl());
        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
