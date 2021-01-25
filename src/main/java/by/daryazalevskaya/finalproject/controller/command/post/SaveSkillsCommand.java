package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.SkillsValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class SaveSkillsCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ValidationCommand validationCommand = new SkillsValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.getServletContext().getRequestDispatcher(PagePath.SKILLS).forward(request, response);
            } else {
                Integer userId = (Integer) request.getSession().getAttribute("user");

                ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
                Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                String skills = request.getParameter("skills");
                if (resume.isPresent()) {
                    resumeService.updateSkills(resume.get(), skills);
                }

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
            }

        } catch (DaoException | TransactionException ex) {
            log.error(ex);
            response.sendError(500);
        }
    }
}
