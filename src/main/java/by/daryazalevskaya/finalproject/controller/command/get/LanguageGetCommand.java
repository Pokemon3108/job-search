package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class LanguageGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        try {
            ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);

            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            if (resume.isPresent()) {
                EmployeeLanguageService employeeLanguageService = (EmployeeLanguageService) serviceFactory.createService(DaoType.EMPLOYEE_LANGUAGE);
                EmployeeLanguage emptyLanguage = resume.get().getLanguage();
                if (emptyLanguage.getId() != null) {
                    Optional<EmployeeLanguage> userLanguage = employeeLanguageService.read(resume.get().getLanguage().getId());
                    userLanguage.ifPresent(employeeLanguage -> request.setAttribute("userLanguage", employeeLanguage));
                }

                request.setAttribute("allLanguages", employeeLanguageService.findAllLanguages());
                request.setAttribute("levels", LanguageLevel.values());
            }

            request.getServletContext()
                    .getRequestDispatcher(PagePath.LANGUAGES)
                    .forward(request, response);

        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}

