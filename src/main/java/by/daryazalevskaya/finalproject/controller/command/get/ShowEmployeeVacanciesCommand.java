package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class ShowEmployeeVacanciesCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory=new ServiceFactoryImpl();
        VacancyService vacancyService= (VacancyService) serviceFactory.createService(DaoType.VACANCY);
        try {
            List<Vacancy> vacancies = vacancyService.findEmployeeVacancies((Integer) request.getSession().getAttribute("user"));
            request.setAttribute("vacancies", vacancies);
            request.getServletContext().getRequestDispatcher(PagePath.EMPLOYEE_VACANCIES).forward(request, response);
        } catch (DaoException ex) {
            log.error(ex);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
