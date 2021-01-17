package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ContactValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.SkillsValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.impl.ResumeServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class SaveSkillsCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        if (Objects.nonNull(session)) {
            try {
                ValidationCommand validationCommand = new SkillsValidationCommand();
                if (!validationCommand.isValid(request, response)) {
                    request.getServletContext().getRequestDispatcher(PagePath.SKILLS).forward(request, response);
                } else {

                    Integer userId = (Integer) request.getSession().getAttribute("user");

                    ResumeService resumeService = new ResumeServiceImpl();
                    resumeService.setTransaction(transaction);
                    Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                    String skills = request.getParameter("skills");

                    resume.ifPresent(resume1 -> resume1.setDescription(skills));
                    if (resume.isPresent()) {
                        resumeService.updateSkills(resume.get());
                    }

                    transaction.commit();
                    response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
                }

            } catch (DaoException | PoolException ex) {
                transaction.rollback();
                log.error(ex);
                response.sendError(500);
            } finally {
                transaction.close();
            }

        }
    }
}
