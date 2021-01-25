package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.service.VacancyComplicatedService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class DeleteVacancyCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        VacancyComplicatedService vacancyService = (VacancyComplicatedService) serviceFactory.createService(DaoType.COMPLICATED_VACANCY);
        try {
            Integer vacancyId = Integer.parseInt(request.getParameter("vacancyId"));
            vacancyService.deleteVacancy(vacancyId);
            response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_VACANCY_LIST.getUrl());

        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}