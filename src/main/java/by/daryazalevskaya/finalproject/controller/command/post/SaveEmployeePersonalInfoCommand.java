package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.PersonalInfoValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.Gender;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.impl.ResumeComplicatedServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeePersonalInfoBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveEmployeePersonalInfoCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");

            ValidationCommand validationCommand = new PersonalInfoValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.setAttribute("genders", Gender.values());
                CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
                request.setAttribute("countries", new SortingService().sortCountriesByAlphabet(countryService.findAll()));
                request.getServletContext().getRequestDispatcher(PagePath.PERSONAL_INFO).forward(request, response);
            } else {

                EmployeePersonalInfoBuilder builder = new EmployeePersonalInfoBuilder();
                EmployeePersonalInfo info = builder.build(request);

                ResumeComplicatedServiceImpl complicatedService=
                        (ResumeComplicatedServiceImpl) serviceFactory.createService(DaoType.COMPLICATED_RESUME);
                complicatedService.saveEmployeePersonalInfo(userId, info);

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());

            }
        } catch ( DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
