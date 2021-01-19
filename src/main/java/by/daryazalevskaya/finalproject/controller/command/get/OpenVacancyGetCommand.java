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
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class OpenVacancyGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            EmployerService employerService = new EmployerServiceImpl();
            employerService.setTransaction(transaction);
            try {
                Optional<Employer> employer = employerService.read(userId);
                if (employer.isPresent() && !employer.get().getCompanyName().isEmpty()) {
                    request.setAttribute("schedules", Schedule.values());
                    request.setAttribute("currencies", Currency.values());
                    request.setAttribute("action", "open");
                    request.getServletContext()
                            .getRequestDispatcher(PagePath.VACANCY)
                            .forward(request, response);

                } else {
                    request.getServletContext()
                            .getRequestDispatcher(PagePath.VACANCY_ERROR)
                            .forward(request, response);
                }
            } catch (DaoException | PoolException e) {
                log.error(e);

            }
        }
    }
}
