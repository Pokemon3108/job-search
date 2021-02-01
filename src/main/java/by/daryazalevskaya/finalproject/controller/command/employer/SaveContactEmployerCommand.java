package by.daryazalevskaya.finalproject.controller.command.employer;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ContactValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.ResumeComplicatedService;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class SaveContactEmployerCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");

            ValidationCommand validationCommand = new ContactValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.getServletContext().getRequestDispatcher(PagePath.CONTACT).forward(request, response);
            } else {
                ContactBuilder contactBuilder = new ContactBuilder();
                Contact contact = contactBuilder.build(request);

                ResumeComplicatedService complicatedService=
                        (ResumeComplicatedService) serviceFactory.createService(DaoType.COMPLICATED_EMPLOYER);
                complicatedService.saveContact(userId, contact);
                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_HOME.getUrl());
            }

        } catch (DaoException | TransactionException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
