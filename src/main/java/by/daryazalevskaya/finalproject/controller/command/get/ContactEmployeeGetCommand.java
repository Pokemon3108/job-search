package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class ContactEmployeeGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
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
        }
    }
}
