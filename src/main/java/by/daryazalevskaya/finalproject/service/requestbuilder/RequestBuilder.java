package by.daryazalevskaya.finalproject.service.requestbuilder;

import javax.servlet.http.HttpServletRequest;


/**
 * The interface Request builder defines operation of building project of the type {@code T}
 */
public interface RequestBuilder<T> {
    /**
     * Build object of type {@code T}
     *
     * @param request the request, from which object will be build
     * @return the built object
     */
    T build(HttpServletRequest request);
}
