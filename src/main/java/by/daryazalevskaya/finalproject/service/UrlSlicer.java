package by.daryazalevskaya.finalproject.service;

import javax.servlet.http.HttpServletRequest;

public class UrlSlicer {
    public String getRelativePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uriRequest = request.getRequestURI();

        return uriRequest.substring(contextPath.length());
    }
}
