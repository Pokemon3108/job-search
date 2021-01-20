package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.controller.CookieOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.ResourceBundle;

public class LocaleService {

    public ResourceBundle createResourceBundle(PageContext pageContext) {
        CookieOperations operations = new CookieOperations();
        String value = operations.findCookieByName((HttpServletRequest) pageContext.getRequest(), "lang").getValue();
        String lang = value.substring(0, value.indexOf('_'));
        String country = value.substring(value.indexOf('_') + 1);
        return ResourceBundle.getBundle("property.pagecontent", new java.util.Locale(lang, country));
    }
}
