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
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class EmployerHomeCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        Integer userId= (Integer) request.getSession().getAttribute("user");
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        EmployerService employerService = new EmployerServiceImpl();
        employerService.setTransaction(transaction);
        try {
            Optional<Employer> employer = employerService.read(userId);
            employer.ifPresent(employer1 -> request.setAttribute("employer", employer1));
            request.getServletContext()
                    .getRequestDispatcher(PagePath.EMPLOYER_HOME)
                    .forward(request, response);
            transaction.commit();
        } catch (DaoException | PoolException e) {
            transaction.rollback();
            log.error(e);
            response.sendError(500, "Database error.");
        } finally {
            transaction.close();
        }
    }
}
