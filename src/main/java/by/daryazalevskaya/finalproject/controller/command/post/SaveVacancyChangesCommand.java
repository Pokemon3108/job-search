package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.VacancyValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.VacancyServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.RequestBuilder;
import by.daryazalevskaya.finalproject.service.requestbuilder.VacancyBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class SaveVacancyChangesCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {

            String action = request.getParameter("action");
            ValidationCommand validationCommand = new VacancyValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.setAttribute("schedules", Schedule.values());
                request.setAttribute("currencies", Currency.values());
                request.setAttribute("action", action);
                request.getServletContext().getRequestDispatcher(PagePath.VACANCY_EDIT).forward(request, response);
            } else {
                RequestBuilder builder = new VacancyBuilder();
                Vacancy vacancy = builder.build(request);

                VacancyService vacancyService = (VacancyService) serviceFactory.createService(DaoType.VACANCY);

                if (action.equals("open")) {
                    vacancyService.addNewVacancy(vacancy);
                } else if (action.equals("edit")) {
                    vacancyService.update(vacancy);
                }

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_VACANCY_LIST.getUrl());
            }

        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
