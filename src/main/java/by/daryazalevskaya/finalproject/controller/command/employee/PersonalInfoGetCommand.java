package by.daryazalevskaya.finalproject.controller.command.employee;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.type.Gender;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.EmployeePersonalInfoService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.utils.SortingService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PersonalInfoGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        try {
            ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);

            EmployeePersonalInfoService infoService = (EmployeePersonalInfoService) serviceFactory.createService(DaoType.EMPLOYEE_PERSONAL_INFO);
            SortingService sortingService = new SortingService();

            CountryService countryService = (CountryService) serviceFactory.createService(DaoType.COUNTRY);
            request.setAttribute("countries", sortingService.sortCountriesByAlphabet(countryService.findAll()));

            EmployeePersonalInfo emptyInfo = resume.get().getPersonalInfo();
            if (emptyInfo.getId() != null) {
                Optional<EmployeePersonalInfo> fullInfo = infoService.read(emptyInfo.getId());
                fullInfo.ifPresent(info -> request.setAttribute("info", info));
            }

            request.setAttribute("genders", Gender.values());

            request.getServletContext()
                    .getRequestDispatcher(PagePath.PERSONAL_INFO)
                    .forward(request, response);

        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
