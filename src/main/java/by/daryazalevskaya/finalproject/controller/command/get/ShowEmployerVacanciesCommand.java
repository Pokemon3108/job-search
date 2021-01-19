package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.impl.VacancyServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Log4j2
public class ShowEmployerVacanciesCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            VacancyService vacancyService=new VacancyServiceImpl();
            vacancyService.setTransaction(transaction);
            try {
                List<Vacancy> vacancies=vacancyService.findVacanciesByEmployerId(userId);
                request.setAttribute("vacancies", vacancies);
                transaction.commit();
                request.getServletContext()
                        .getRequestDispatcher(PagePath.VACANCY_LIST)
                        .forward(request, response);
            } catch (DaoException e) {
                transaction.rollback();
                log.error(e);
                response.sendError(500);
            }finally {
                transaction.close();
            }

        }
    }
}
