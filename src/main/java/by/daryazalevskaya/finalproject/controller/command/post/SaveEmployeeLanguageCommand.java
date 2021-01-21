package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeeLanguageBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class SaveEmployeeLanguageCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");

            EmployeeLanguageService languageService = (EmployeeLanguageService) serviceFactory.createService(DaoType.EMPLOYEE_LANGUAGE);

            EmployeeLanguageBuilder builder = new EmployeeLanguageBuilder();
            EmployeeLanguage language = builder.build(request);

            if (Objects.nonNull(language.getId())) {
                languageService.update(language);
            } else {
                Integer languageId = languageService.addNewLanguage(language);
                language.setId(languageId);

                ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
                Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                if (resume.isPresent()) {
                    resumeService.createLanguage(resume.get(), language);
                }
            }

            response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
