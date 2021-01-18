package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.EmployeeInfoValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.*;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployerBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class SaveEmployerInfoCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        HttpSession session = request.getSession(false);

        try {
            if (Objects.nonNull(session)) {

                ValidationCommand validationCommand=new EmployeeInfoValidationCommand();
                if (!validationCommand.isValid(request,response)) {
                    request.getServletContext().getRequestDispatcher(PagePath.EMPLOYER_INFO).forward(request, response);
                } else {

                    EmployerService employerService = new EmployerServiceImpl();
                    employerService.setTransaction(transaction);

                    EmployerBuilder builder = new EmployerBuilder();
                    Employer employer = builder.build(request);
                    employerService.update(employer);

                    transaction.commit();

                    response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_HOME.getUrl());
                }
            }
        }catch (InsertIdDataBaseException | DaoException | PoolException e) {
            transaction.rollback();
            log.error(e);
            response.sendError(500);
        } finally {
            transaction.close();
        }
    }
}
