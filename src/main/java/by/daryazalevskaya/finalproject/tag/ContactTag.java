package by.daryazalevskaya.finalproject.tag;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.service.utils.LocaleService;
import lombok.Setter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Contact tag class refers to JSTL custom tag
 */
@Setter
public class ContactTag extends TagSupport {
    private Contact contact;

    @Override
    public int doStartTag() throws JspException {
        if (Objects.nonNull(contact)) {
            JspWriter out = pageContext.getOut();
            LocaleService localeService = new LocaleService();
            ResourceBundle rb = localeService.createResourceBundle(pageContext);
            try {
                if (Objects.nonNull(contact.getTelephone()) && !contact.getTelephone().isEmpty()) {
                    out.write("<p><strong>" + rb.getString("phone") + ": </strong>");
                    out.write("<span>" + contact.getTelephone() + "</span></p>");
                }
                if (Objects.nonNull(contact.getEmail())) {
                    out.write("<p><strong>" + rb.getString("email") + ": </strong>");
                    out.write("<span>" + contact.getEmail() + "</span></p>");
                }
                if (Objects.nonNull(contact.getSkype()) && !contact.getSkype().isEmpty() ) {
                    out.write("<p><strong>" + "Skype" + ": </strong>");
                    out.write("<span>" + contact.getSkype() + "</span></p>");
                }
            } catch (IOException ex) {
                throw new JspException(ex.getMessage());
            }
        }

        return SKIP_BODY;
    }
}
