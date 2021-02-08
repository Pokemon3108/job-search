package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.requestbuilder.ContactRequestBuilder;
import by.daryazalevskaya.finalproject.service.validator.ContactValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class ContactValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        ContactRequestBuilder contactRequestBuilder = new ContactRequestBuilder();
        Contact contact = contactRequestBuilder.build(request);

        ContactValidator validator = new ContactValidator();

        if (!validator.isValidEmail(contact.getEmail())) {
            isValid=false;
            request.setAttribute("isInvalidEmail", true);
        }
        if (!validator.isValidPhone(contact.getTelephone())) {
            isValid=false;
            request.setAttribute("isInvalidTelephone", true);
        }
        if (!contact.getSkype().equals("") && !validator.isValidSkype(contact.getSkype())) {
            isValid=false;
            request.setAttribute("isInvalidSkype", true);
        }

        if (!isValid) {
            request.setAttribute("contact", contact);
            log.error("Invalid contact format");
            isValid=false;
        }

        return isValid;
    }
}
