package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;

import javax.servlet.http.HttpServletRequest;

public class VacancyParamsBuilder implements RequestBuilder<VacancySearchParams> {

    @Override
    public VacancySearchParams build(HttpServletRequest request) {
        String position=request.getParameter("position");
        VacancySearchParams params=new VacancySearchParams();
        params.setPosition(position);
        if (request.getParameter("countryId")!="") {
            Integer countryId=Integer.parseInt(request.getParameter("countryId"));
            params.setCountryId(countryId);
        }
        if (request.getParameter("specializationId")!="") {
            Integer specializationId=Integer.parseInt(request.getParameter("specializationId"));
            params.setSpecializationId(specializationId);
        }
        return params;
    }
}
