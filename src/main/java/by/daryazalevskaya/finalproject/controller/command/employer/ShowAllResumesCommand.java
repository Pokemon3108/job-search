package by.daryazalevskaya.finalproject.controller.command.employer;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ResumeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
public class ShowAllResumesCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResumeService resumeService= (ResumeService) serviceFactory.createService(DaoType.RESUME);
        try {
            List<Resume> resumeList=resumeService.findAll();
            request.setAttribute("resumeList", resumeList);
            request.getServletContext().getRequestDispatcher(PagePath.RESUME_LIST).forward(request, response);
        } catch (DaoException e) {
            log.error(e);
            response.sendError(500);
        }
    }
}
