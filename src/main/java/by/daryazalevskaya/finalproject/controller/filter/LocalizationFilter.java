package by.daryazalevskaya.finalproject.controller.filter;

import by.daryazalevskaya.finalproject.model.type.Localization;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "localizationFilter")
public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            servletRequest.setAttribute("languages", Localization.values());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
