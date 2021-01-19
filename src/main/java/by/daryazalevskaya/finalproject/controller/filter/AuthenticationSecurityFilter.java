package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "authFilter")
public class AuthenticationSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            HttpSession session = httpRequest.getSession(false);
            if (session.getAttribute("user") == null) {
                log.error("User wasn't authenticated");
                httpResponse.sendRedirect(httpRequest.getContextPath() + UriPattern.LOGIN.getUrl());
            }
          else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
