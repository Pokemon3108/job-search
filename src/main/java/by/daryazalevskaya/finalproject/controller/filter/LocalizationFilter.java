package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.controller.CookieOperations;
import by.daryazalevskaya.finalproject.model.Localization;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/job/*")
public class LocalizationFilter implements Filter {

    final Localization DEFAULT_LANG = Localization.ENGLISH;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            final String cookieName="lang";
            CookieOperations operations=new CookieOperations();
            Cookie cookie=operations.findCookieByName(httpRequest, cookieName);

            if (!Objects.nonNull(cookie)) {

                servletRequest.setAttribute("localization", DEFAULT_LANG.getLanguage());
                cookie = new Cookie("lang", DEFAULT_LANG.getLanguage());
                if (servletResponse instanceof HttpServletResponse) {
                    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                    httpResponse.addCookie(cookie);
                }
            }

            servletRequest.setAttribute("localization", cookie.getValue());

        }
    }
}
