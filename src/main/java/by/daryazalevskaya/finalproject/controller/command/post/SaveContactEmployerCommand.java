package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ContactValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class SaveContactEmployerCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            Integer userId = (Integer) request.getSession().getAttribute("user");

            ContactService contactService = (ContactService) serviceFactory.createService(DaoType.CONTACT);

            ValidationCommand validationCommand = new ContactValidationCommand();
            if (!validationCommand.isValid(request, response)) {
                request.getServletContext().getRequestDispatcher(PagePath.CONTACT).forward(request, response);
            } else {
                ContactBuilder contactBuilder = new ContactBuilder();
                Contact contact = contactBuilder.build(request);
                if (Objects.nonNull(contact.getId())) {
                    contactService.update(contact);
                } else {
                    Integer contactId = contactService.addNewContact(contact);
                    contact.setId(contactId);

                    EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
                    employerService.createContact(userId, contact);

                }
                response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_HOME.getUrl());
            }

        } catch (DaoException | TransactionException e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
