package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.employer.Employer;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployerBuilder;
import by.daryazalevskaya.finalproject.service.validator.EmployerInfoValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class EmployeeInfoValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        EmployerBuilder builder=new EmployerBuilder();
        Employer employer=builder.build(request);

        EmployerInfoValidator validator=new EmployerInfoValidator();
        if (!validator.isValidCity(employer.getCity())) {
            isValid=false;
            request.setAttribute("invalidCity", true);
        }

        if (!validator.isValidCompanyName(employer.getCompanyName())) {
            isValid=false;
            request.setAttribute("invalidCompany", true);
        }

        if (!isValid) {
            request.setAttribute("employer", employer);
            log.info("Invalid employer info format with id: ", request.getSession().getAttribute("user"));
        }

        return isValid;

    }
}
