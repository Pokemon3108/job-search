package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeePersonalInfoBuilder;
import by.daryazalevskaya.finalproject.service.validator.PersonalInfoValidator;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class PersonalInfoValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        EmployeePersonalInfoBuilder builder=new EmployeePersonalInfoBuilder();
        EmployeePersonalInfo info=builder.build(request);
        PersonalInfoValidator validator=new PersonalInfoValidator();

        int errors = 0;
        if (!validator.isValidName(info.getName())) {
            ++errors;
            request.setAttribute("invalidName", true);
        }
        if (!info.getSurname().isEmpty() && !validator.isValidName(info.getSurname())) {
            ++errors;
            request.setAttribute("invalidSurname", true);
        }
        if (!info.getCity().isEmpty() &&!validator.isValidName(info.getCity())) {
            ++errors;
            request.setAttribute("invalidCity", true);
        }
        if (!validator.isValidBirthday(info.getBirthday())) {
            ++errors;
            request.setAttribute("invalidBirthday", true);
        }

        if (errors != 0) {
            request.setAttribute("info", info);
            log.info("Invalid employee personal info format");
            isValid = false;
        }
        return isValid;

    }
}
