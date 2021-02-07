package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employer.Employer;

import javax.servlet.http.HttpServletRequest;

/**
 * The  EmployerBuilder is used for creation {@code Employer} object from httpRequest
 * {@link by.daryazalevskaya.finalproject.model.employer.Employer}
 */
public class EmployerBuilder implements RequestBuilder<Employer> {
    @Override
    public Employer build(HttpServletRequest request) {
        Country country=new Country(Integer.parseInt(request.getParameter("country")));
        String city=request.getParameter("city");
        String companyName=request.getParameter("companyName");
        Integer id=Integer.parseInt(request.getParameter("id"));

        return Employer.builder()
                .city(city)
                .companyName(companyName)
                .country(country)
                .id(id)
                .build();

    }
}
