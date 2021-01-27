package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;

import javax.servlet.http.HttpServletRequest;

public class VacancyParamsBuilder implements RequestBuilder {

    @Override
    public VacancySearchParams build(HttpServletRequest request) {
        String position=request.getParameter("position");
        VacancySearchParams params=new VacancySearchParams();
        params.setPosition(position);
        if (request.getParameter("country_id")!=null) {
            Integer countryId=Integer.parseInt(request.getParameter("country_id"));
            params.setCountryId(countryId);
        }
        if (request.getParameter("specialization_id")!=null) {
            Integer specializationId=Integer.parseInt(request.getParameter("specialization_id"));
            params.setSpecializationId(specializationId);
        }
        return params;
    }
}
