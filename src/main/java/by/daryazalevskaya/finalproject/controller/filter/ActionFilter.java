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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "actionFilter")
public class ActionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

            //  String s=httpServletRequest.getServletPath();
            UriPattern uri = UriPattern.getEnumByUri(httpServletRequest.getServletPath());

            if (uri != null) {
                switch (httpServletRequest.getMethod()) {
                    case "GET":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandGet(uri));
                        break;
                    case "POST":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandPost(uri));
                        break;
                }
                httpServletRequest.getRequestDispatcher("/controller").forward(servletRequest, servletResponse);
            } else {
                log.error(String.format("Requested URI %s does not contains command.", ((HttpServletRequest) servletRequest).getRequestURL()));
                filterChain.doFilter(servletRequest, servletResponse);
            }

        }
    }


}
