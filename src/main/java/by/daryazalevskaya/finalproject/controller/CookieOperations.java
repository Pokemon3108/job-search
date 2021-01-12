package by.daryazalevskaya.finalproject.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieOperations {

    public Cookie findCookieByName(HttpServletRequest request, final String name) {
        Cookie cookies[]=request.getCookies();
        Cookie cookie = null;
        if (cookies!=null) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }


        return cookie;
    }
}
