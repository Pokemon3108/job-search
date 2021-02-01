package by.daryazalevskaya.finalproject.controller.command.employee;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.service.VacancyService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RespondVacancyCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);
        try {
            vacancyService.addEmployeeVacancy(Integer.parseInt(request.getParameter("vacancyId")),
                    (int)request.getSession().getAttribute("user"));
            response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
        } catch (DaoException | TransactionException ex) {
            log.error(ex);
            response.sendError(500);
        }
    }
}
