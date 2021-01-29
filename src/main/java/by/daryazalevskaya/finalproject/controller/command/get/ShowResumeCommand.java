package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ResumeService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class ShowResumeCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer resumeId = Integer.parseInt(request.getParameter("id"));
            ResumeService resumeService= (ResumeService) serviceFactory.createService(DaoType.RESUME);
            Optional<Resume> resume=resumeService.read(resumeId);
            resume.ifPresent(resume1 -> request.setAttribute("resume", resume1));
            request.getServletContext().getRequestDispatcher(PagePath.SHOW_RESUME).forward(request, response);
        } catch (NumberFormatException ex) {
            log.error("Illegal resume id argument");
            response.sendError(400);
        } catch (DaoException | TransactionException e) {
           log.error(e);
           response.sendError(500);
        }

    }
}
