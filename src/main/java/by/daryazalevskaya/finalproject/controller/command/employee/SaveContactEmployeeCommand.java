package by.daryazalevskaya.finalproject.controller.command.employee;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ContactValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.impl.ResumeComplicatedServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactRequestBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveContactEmployeeCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            ValidationCommand validationCommand = new ContactValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.getServletContext().getRequestDispatcher(PagePath.CONTACT).forward(request, response);
            } else {
                ContactRequestBuilder contactRequestBuilder = new ContactRequestBuilder();
                Contact contact = contactRequestBuilder.build(request);
                ResumeComplicatedServiceImpl complicatedService =
                        (ResumeComplicatedServiceImpl) serviceFactory.createService(DaoType.COMPLICATED_RESUME);
                complicatedService.saveContact(userId, contact);

                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
            }

        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
