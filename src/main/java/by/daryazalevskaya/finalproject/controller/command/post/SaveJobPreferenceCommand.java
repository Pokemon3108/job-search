package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.JobPreferenceValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.ResumeComplicatedServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.JobPreferenceBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveJobPreferenceCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            JobPreferenceService jobPreferenceService = (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);

            ValidationCommand validationCommand = new JobPreferenceValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.setAttribute("specializations", jobPreferenceService.findAllSpecializations());
                request.setAttribute("currencies", Currency.values());
                request.setAttribute("schedules", Schedule.values());
                request.getServletContext().getRequestDispatcher(PagePath.JOB_PREFERENCE).forward(request, response);
            } else {

                JobPreferenceBuilder builder = new JobPreferenceBuilder();
                JobPreference preference = builder.build(request);
                ResumeComplicatedServiceImpl complicatedService=
                        (ResumeComplicatedServiceImpl) serviceFactory.createService(DaoType.COMPLICATED_RESUME);
                complicatedService.saveEmployeePersonalInfo(userId, preference);

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
            }


        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
