package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployerService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class EmployerGetContactCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        try {
            EmployerService employerService = (EmployerService) serviceFactory.createService(DaoType.EMPLOYER);
            Optional<Employer> employer = employerService.read(userId);

            ContactService contactService = (ContactService) serviceFactory.createService(DaoType.CONTACT);
            if (employer.isPresent() && employer.get().getContact() != null) {
                Optional<Contact> fullContact = contactService.read(employer.get().getContact().getId());
                fullContact.ifPresent(contact1 -> request.setAttribute("contact", contact1));
            }

            request.getServletContext()
                    .getRequestDispatcher(PagePath.CONTACT)
                    .forward(request, response);

        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
