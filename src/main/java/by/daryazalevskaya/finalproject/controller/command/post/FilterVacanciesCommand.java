package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.dto.VacancySearchParams;
import by.daryazalevskaya.finalproject.service.VacancyService;
import by.daryazalevskaya.finalproject.service.requestbuilder.RequestBuilder;
import by.daryazalevskaya.finalproject.service.requestbuilder.VacancyParamsBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class FilterVacanciesCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestBuilder builder=new VacancyParamsBuilder();
        VacancySearchParams params=builder.build(request);
        VacancyService vacancyService= (VacancyService) serviceFactory.createService(DaoType.VACANCY);
        try {
            vacancyService.readVacancyByParams(params);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
