package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import javax.servlet.http.HttpServletRequest;

/**
 * The  VacancyBuilder is used for creation {@code Vacancy} object from httpRequest
 * {@link by.daryazalevskaya.finalproject.model.employer.Vacancy}
 */
public class VacancyBuilder implements RequestBuilder<Vacancy> {
    @Override
    public Vacancy build(HttpServletRequest request) {
        String position=request.getParameter("position");
        String city=request.getParameter("city");
        Schedule schedule=Schedule.valueOf(request.getParameter("schedule"));
        Currency currency=Currency.valueOf(request.getParameter("currency"));
        String requirements=request.getParameter("requirements");
        String duties=request.getParameter("duties");
        Integer specialization = Integer.parseInt(request.getParameter("specialization"));
        Country country=new Country(Integer.parseInt(request.getParameter("country")));
        Integer userId = (Integer) request.getSession().getAttribute("user");

        Vacancy vacancy=Vacancy.builder()
                .city(city)
                .currency(currency)
                .requirements(requirements)
                .duties(duties)
                .schedule(schedule)
                .position(position)
                .employer(new Employer(userId))
                .specialization(new Specialization(specialization))
                .country(country)
                .build();

        if (!request.getParameter("salary").isEmpty()) {
            vacancy.setSalary(Integer.parseInt(request.getParameter("salary")));
        }

        if (!request.getParameter("id").isEmpty()) {
            vacancy.setId(Integer.parseInt(request.getParameter("id")));
        }

        return vacancy;
    }
}
