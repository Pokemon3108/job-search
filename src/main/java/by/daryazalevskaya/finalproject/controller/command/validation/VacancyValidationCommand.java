package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.requestbuilder.RequestBuilder;
import by.daryazalevskaya.finalproject.service.requestbuilder.VacancyBuilder;
import by.daryazalevskaya.finalproject.service.validator.VacancyValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class VacancyValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        RequestBuilder<Vacancy> builder=new VacancyBuilder();
        Vacancy vacancy=builder.build(request);
        VacancyValidator vacancyValidator=new VacancyValidator();

        boolean isValid=true;
        if (!vacancyValidator.isValidCity(vacancy.getCity())) {
            isValid=false;
            request.setAttribute("invalidCity", true);
        }
        if (!vacancyValidator.isValidLongText(vacancy.getRequirements())) {
            isValid=false;
            request.setAttribute("invalidRequirements", true);
        }
        if (!vacancyValidator.isValidLongText(vacancy.getDuties())) {
            isValid=false;
            request.setAttribute("invalidDuties", true);
        }
        if (!vacancyValidator.isValidPosition(vacancy.getPosition())) {
            isValid=false;
            request.setAttribute("invalidPosition", true);
        }

        if (!isValid) {
            request.setAttribute("vacancy", vacancy);
            log.info("Invalid vacancy format with id: ", vacancy.getId());
        }

        return isValid;
    }
}
