package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.service.validator.SkillsValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SkillsValidationCommand implements ValidationCommand {
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid=true;
        SkillsValidator skillsValidator=new SkillsValidator();
        if(! skillsValidator.isValidSkills(request.getParameter("skills"))) {
            request.setAttribute("invalidSkills", true);
            isValid=false;
        }

        return isValid;
    }
}
