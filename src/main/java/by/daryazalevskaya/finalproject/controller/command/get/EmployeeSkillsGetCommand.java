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
public class EmployeeSkillsGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            try {
                ResumeService resumeService = new ResumeServiceImpl();
                resumeService.setTransaction(transaction);
                Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                resume.ifPresent(resume1 -> request.setAttribute("skills", resume1.getSkills()));

                transaction.commit();
                request.getServletContext()
                        .getRequestDispatcher(PagePath.SKILLS)
                        .forward(request, response);
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

