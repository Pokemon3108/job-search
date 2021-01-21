package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.Employee;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployeeService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.ContactServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployeeServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.ResumeServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class ContactEmployeeGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);

            ContactService contactService = (ContactService) serviceFactory.createService(DaoType.CONTACT);
            Contact emptyContact = resume.get().getContact();

            if (emptyContact.getId() != null) {
                Optional<Contact> fullContact = contactService.read(emptyContact.getId());
                fullContact.ifPresent(contact1 -> request.setAttribute("contact", contact1));
            }

            request.getServletContext()
                    .getRequestDispatcher(PagePath.CONTACT)
                    .forward(request, response);

        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
