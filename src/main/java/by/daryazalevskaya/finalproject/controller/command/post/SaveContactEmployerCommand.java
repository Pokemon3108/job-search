package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ContactValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import by.daryazalevskaya.finalproject.service.impl.ContactServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployerServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j2
public class SaveContactEmployerCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        try {
                Integer userId = (Integer) request.getSession().getAttribute("user");

                ContactService contactService = new ContactServiceImpl();
                contactService.setTransaction(transaction);

                ValidationCommand validationCommand = new ContactValidationCommand();
                if (!validationCommand.isValid(request, response)) {
                    request.getServletContext().getRequestDispatcher(PagePath.CONTACT).forward(request, response);
                } else {
                    ContactBuilder contactBuilder = new ContactBuilder();
                    Contact contact = contactBuilder.build(request);
                    if (Objects.nonNull(contact.getId())) {
                        contactService.update(contact);
                    } else {
                        Integer contactId = contactService.addNewEntity(contact);
                        contact.setId(contactId);

                        EmployerService employerService=new EmployerServiceImpl();
                        employerService.setTransaction(transaction);
                        employerService.createContact(userId, contact);

                    }

                    transaction.commit();
                    response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYER_HOME.getUrl());
                }

        } catch (PoolException | InsertIdDataBaseException | DaoException | TransactionException e) {
            transaction.rollback();
            log.error(e);
            response.sendError(500);
        } finally {
            transaction.close();
        }
    }
}
