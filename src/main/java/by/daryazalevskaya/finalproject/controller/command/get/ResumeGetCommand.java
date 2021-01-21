package by.daryazalevskaya.finalproject.controller.command.get;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployeePersonalInfoService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactoryImpl;
import by.daryazalevskaya.finalproject.service.impl.ContactServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.EmployeePersonalInfoServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.JobPreferenceServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.ResumeServiceImpl;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class ResumeGetCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        try {
            ResumeService resumeService = (ResumeService) serviceFactory.createService(DaoType.RESUME);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            if (resume.isPresent()) {
                resumeService.fillResume(resume.get());
                request.setAttribute("resume", resume.get());
            }

            request.getServletContext()
                    .getRequestDispatcher(PagePath.EMPLOYEE_HOME)
                    .forward(request, response);

        } catch (DaoException  ex) {
            log.error(ex);
            response.sendError(500);
        } finally {
            serviceFactory.close();
        }
    }
}
