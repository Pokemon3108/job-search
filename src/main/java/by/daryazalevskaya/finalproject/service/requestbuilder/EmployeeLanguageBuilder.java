package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;

import javax.servlet.http.HttpServletRequest;

public class EmployeeLanguageBuilder implements RequestBuilder {
    @Override
    public EmployeeLanguage build(HttpServletRequest request) {
        int languageId=Integer.parseInt(request.getParameter("language"));
        LanguageLevel level=LanguageLevel.valueOf(request.getParameter("level"));

        EmployeeLanguage language= EmployeeLanguage.builder()
                .level(level)
                .name(new Language(languageId))
                .build();

        if (!request.getParameter("lang_id").isEmpty()) {
            language.setId(Integer.parseInt(request.getParameter("lang_id")));
        }

        return language;
    }
}
