package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.model.type.Role;
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
@WebFilter(filterName = "roleFilter")
public class RoleSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

            String uri = httpRequest.getServletPath();
            final String start="/job";
            String roleUrl = uri.substring(start.length()+1, uri.indexOf("/", start.length()+1));

            HttpSession session = httpRequest.getSession(false);
            Role role = (Role) session.getAttribute("role");
            if (!role.toString().toLowerCase().equals(roleUrl)) {
                log.error(String.format("User with id %d has no authorities for this mapping.", session.getAttribute("user")));
                httpResponse.sendError(404, "You haven't any access on this page.");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }
}
