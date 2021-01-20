package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.impl.EmployeeLanguageServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.ResumeServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeeLanguageBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class SaveEmployeeLanguageCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        HttpSession session = request.getSession(false);

        try {
            if (Objects.nonNull(session)) {
                Integer userId = (Integer) request.getSession().getAttribute("user");

                EmployeeLanguageService languageService=new EmployeeLanguageServiceImpl();
                languageService.setTransaction(transaction);

                EmployeeLanguageBuilder builder=new EmployeeLanguageBuilder();
                EmployeeLanguage language=builder.build(request);

                if (Objects.nonNull(language.getId())) {
                    languageService.update(language);
                } else {
                    Integer languageId=languageService.addNewEntity(language);
                    language.setId(languageId);

                    ResumeService resumeService = new ResumeServiceImpl();
                    resumeService.setTransaction(transaction);
                    Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                    if (resume.isPresent()) {
                        resumeService.createLanguage(resume.get(), language);
                    }
                }

                transaction.commit();
                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());

            }
        } catch (DaoException | PoolException | InsertIdDataBaseException e) {
            transaction.rollback();
            log.error(e);
            response.sendError(500);
        } finally {
            transaction.close();
        }
    }
}
