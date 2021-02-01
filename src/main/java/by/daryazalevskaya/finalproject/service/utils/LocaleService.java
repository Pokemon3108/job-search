package by.daryazalevskaya.finalproject.service.utils;

import by.daryazalevskaya.finalproject.controller.CookieOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Objects;
import java.util.ResourceBundle;

public class LocaleService {

    public ResourceBundle createResourceBundle(PageContext pageContext) {
        CookieOperations operations = new CookieOperations();
        Cookie cookie=operations.findCookieByName((HttpServletRequest) pageContext.getRequest(), "lang");
        if (Objects.nonNull(cookie)) {
            String value = cookie.getValue();
            String lang = value.substring(0, value.indexOf('_'));
            String country = value.substring(value.indexOf('_') + 1);
            return ResourceBundle.getBundle("property.pagecontent", new java.util.Locale(lang, country));
        }
        return ResourceBundle.getBundle("property.pagecontent");
    }
}
