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
import java.util.stream.Stream;

@Log4j2
public class ContactEmployeeGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            Integer userId = (Integer) request.getSession().getAttribute("user");
            TransactionFactory factory = new TransactionFactoryImpl();
            Transaction transaction = factory.createTransaction();
            try {
                EmployeeService employeeService = new EmployeeServiceImpl();
                employeeService.setTransaction(transaction);
                Employee employee = employeeService.read(userId).get();

                ContactService contactService = new ContactServiceImpl();
                contactService.setTransaction(transaction);
                ResumeService resumeService = new ResumeServiceImpl();
                resumeService.setTransaction(transaction);
                Optional<Resume> resume = resumeService.read(employee.getResume().getId());

                Contact emptyContact = resume.get().getContact();

                if (emptyContact.getId()!=null) {
                    Optional<Contact> fullContact = contactService.read(emptyContact.getId());
                    fullContact.ifPresent(contact1 -> request.setAttribute("contact", contact1));
                }

                transaction.commit();
                request.getServletContext()
                        .getRequestDispatcher(PagePath.CONTACT)
                        .forward(request, response);

            } catch (DaoException | PoolException | TransactionException e) {
                transaction.rollback();
                log.error(e);
                response.sendError(500);
            } finally {
                transaction.close();
            }

        }
    }
}
