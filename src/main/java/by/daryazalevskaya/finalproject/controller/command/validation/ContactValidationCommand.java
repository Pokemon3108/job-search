package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactBuilder;
import by.daryazalevskaya.finalproject.service.validator.ContactValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ContactValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        ContactBuilder contactBuilder = new ContactBuilder();
        Contact contact = contactBuilder.build(request);

        ContactValidator validator = new ContactValidator();

        int errors=0;
        if (!validator.isValidEmail(contact.getEmail())) {
            ++errors;
            request.setAttribute("isInvalidEmail", true);
        }
        if (!validator.isValidPhone(contact.getTelephone())) {
            ++errors;
            request.setAttribute("isInvalidTelephone", true);
        }

        if (errors!=0) {
            request.setAttribute("contact", contact);
            log.error("Invalid contact format");
            isValid=false;
        }

        return isValid;
    }
}
