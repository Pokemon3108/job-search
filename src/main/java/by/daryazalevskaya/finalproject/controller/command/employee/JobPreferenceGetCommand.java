package by.daryazalevskaya.finalproject.controller.command.employee;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class JobPreferenceGetCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        try {
            ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);

            JobPreferenceService jobPreferenceService = (JobPreferenceService) serviceFactory.createService(DaoType.JOB_PREFERENCE);

            if (resume.isPresent()) {
                JobPreference emptyPreference = resume.get().getJobPreference();
                if (emptyPreference.getId() != null) {
                    Optional<JobPreference> fullPreference = jobPreferenceService.read(emptyPreference.getId());
                    fullPreference.ifPresent(fullPreference1 -> request.setAttribute("preference", fullPreference1));
                }
            }

            request.setAttribute("specializations", jobPreferenceService.findAllSpecializations());
            request.setAttribute("currencies", Currency.values());
            request.setAttribute("schedules", Schedule.values());

            request.getServletContext()
                    .getRequestDispatcher(PagePath.JOB_PREFERENCE)
                    .forward(request, response);

        } catch (DaoException  e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
