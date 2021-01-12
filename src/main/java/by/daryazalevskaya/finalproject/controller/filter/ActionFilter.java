package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.CommandStorage;
import by.daryazalevskaya.finalproject.service.UrlSlicer;
import lombok.extern.log4j.Log4j2;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = "/job/*", dispatcherTypes = DispatcherType.REQUEST)
public class ActionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

            UrlSlicer urlSlicer = new UrlSlicer();
            UriPattern uri = UriPattern.getEnumByUri(urlSlicer.getRelativePath(httpServletRequest));

            if (uri != null) {
                switch (httpServletRequest.getMethod()) {
                    case "GET":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandGet(uri));
                        break;
                    case "POST":
                        servletRequest.setAttribute("command", CommandStorage.getInstance().getCommandPost(uri));
                        break;
                }
                filterChain.doFilter(servletRequest, servletResponse);
                httpServletRequest.getRequestDispatcher("/controller").forward(servletRequest, servletResponse);
            } else {
                log.error(String.format("Requested URI %s does not contains command.", ((HttpServletRequest) servletRequest).getRequestURL()));
                filterChain.doFilter(servletRequest, servletResponse);

            }

        }
    }


}
