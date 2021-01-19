package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.impl.VacancyServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class EditVacancyCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer vacancyId=Integer.parseInt(request.getParameter("vacancyId"));
            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            VacancyService vacancyService=new VacancyServiceImpl();
            vacancyService.setTransaction(transaction);
            try {
                Optional<Vacancy> vacancy=vacancyService.read(vacancyId);
                if (vacancy.isPresent()) {
                    request.setAttribute("schedules", Schedule.values());
                    request.setAttribute("currencies", Currency.values());
                    request.setAttribute("action", "edit");
                    request.setAttribute("vacancy", vacancy.get());
                    transaction.commit();
                    request.getServletContext()
                            .getRequestDispatcher(PagePath.VACANCY)
                            .forward(request, response);
                } else {
                    transaction.rollback();
                    response.sendError(404);
                }
            } catch (DaoException | PoolException e) {
                transaction.rollback();
                log.error(e);
                response.sendError(500);
            } finally {
                transaction.close();
            }
        }
    }
}
