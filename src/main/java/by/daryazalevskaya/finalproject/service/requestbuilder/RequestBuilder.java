package by.daryazalevskaya.finalproject.service.requestbuilder;

import javax.servlet.http.HttpServletRequest;

public interface RequestBuilder<T> {
    T build(HttpServletRequest request);
}
