package by.daryazalevskaya.finalproject.controller.command.employee;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.service.impl.ResumeComplicatedServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeeLanguageBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveEmployeeLanguageCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");

            EmployeeLanguageBuilder builder = new EmployeeLanguageBuilder();
            EmployeeLanguage language = builder.build(request);

            ResumeComplicatedServiceImpl complicatedService =
                    (ResumeComplicatedServiceImpl) serviceFactory.createService(DaoType.COMPLICATED_RESUME);
            complicatedService.saveEmployeeLanguage(userId, language);

            response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
