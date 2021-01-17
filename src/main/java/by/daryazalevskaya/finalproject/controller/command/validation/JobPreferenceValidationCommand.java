package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.service.requestbuilder.JobPreferenceBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class JobPreferenceValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        JobPreferenceBuilder builder=new JobPreferenceBuilder();
        JobPreference preference=builder.build(request);

        if (preference.getExperience()<0) {
            isValid=false;
            request.setAttribute("invalidExperience", true);
        }
        if (preference.getSalary()<0) {
            isValid=false;
            request.setAttribute("invalidSalary", true);
        }
        if (preference.getPosition().length()>255) {
            isValid=false;
            request.setAttribute("invalidPosition", true);
        }

        if (!isValid) {
            request.setAttribute("preference", preference);
            log.info("Invalid employee job preference format with id: ", request.getSession().getAttribute("user"));
        }

        return isValid;
    }
}
