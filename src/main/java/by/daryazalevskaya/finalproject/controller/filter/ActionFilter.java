package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.CommandStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@WebFilter
public class ActionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String contextPath = httpServletRequest.getContextPath();
            String uriRequest = httpServletRequest.getRequestURI();
            contextPath = contextPath.substring(uriRequest.length());

            UriPattern uri = UriPattern.getEnumByUri(contextPath);

            if (uri!=null) {
                switch (httpServletRequest.getMethod()) {
                    case "GET":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandGet(uri));
                        break;
                    case "POST":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandPost(uri));
                        break;
                }
            } else {
                log.error("Requested URI can't be processed by server.");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


}