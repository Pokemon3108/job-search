package by.daryazalevskaya.finalproject.tag;

import by.daryazalevskaya.finalproject.controller.CookieOperations;
import by.daryazalevskaya.finalproject.service.EmployeePersonalInfoService;
import by.daryazalevskaya.finalproject.service.impl.EmployeePersonalInfoServiceImpl;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

@Setter
public class AgeTag extends TagSupport {
    private LocalDate birthday;

    @Override
    public int doStartTag() throws JspException {
        if (birthday != null) {
            JspWriter out = pageContext.getOut();
            EmployeePersonalInfoService infoService = new EmployeePersonalInfoServiceImpl();
            int age = infoService.countAge(birthday);
            CookieOperations operations = new CookieOperations();
            String value = operations.findCookieByName((HttpServletRequest) pageContext.getRequest(), "lang").getValue();
            String lang = value.substring(0, value.indexOf('_'));
            String country = value.substring(value.indexOf('_'), value.length());
            ResourceBundle rb = ResourceBundle.getBundle("property.pagecontent", new Locale(lang, country));
            try {
                out.write("<p>");
                out.write("<strong>" + rb.getString("age") + " </strong>");
                out.write("<span>" + age + " " + rb.getString("years") + "</span>");
                out.write("</p>");
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        }
        return SKIP_BODY;
    }
}
