package by.daryazalevskaya.finalproject.controller.command.post;

import by.daryazalevskaya.finalproject.controller.PagePath;
import by.daryazalevskaya.finalproject.controller.UriPattern;
import by.daryazalevskaya.finalproject.controller.command.ActionCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.JobPreferenceValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.PersonalInfoValidationCommand;
import by.daryazalevskaya.finalproject.controller.command.validation.ValidationCommand;
import by.daryazalevskaya.finalproject.dao.exception.*;
import by.daryazalevskaya.finalproject.dao.transaction.Transaction;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactory;
import by.daryazalevskaya.finalproject.dao.transaction.TransactionFactoryImpl;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Gender;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import by.daryazalevskaya.finalproject.service.CountryService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.ResumeService;
import by.daryazalevskaya.finalproject.service.SortingService;
import by.daryazalevskaya.finalproject.service.impl.CountryServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.JobPreferenceServiceImpl;
import by.daryazalevskaya.finalproject.service.impl.ResumeServiceImpl;
import by.daryazalevskaya.finalproject.service.requestbuilder.EmployeePersonalInfoBuilder;
import by.daryazalevskaya.finalproject.service.requestbuilder.JobPreferenceBuilder;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Log4j2
public class SaveJobPreferenceCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException, TransactionException {
        TransactionFactory factory = new TransactionFactoryImpl();
        Transaction transaction = factory.createTransaction();
        HttpSession session = request.getSession(false);

        try {
            if (Objects.nonNull(session)) {
                Integer userId = (Integer) request.getSession().getAttribute("user");
                JobPreferenceService jobPreferenceService = new JobPreferenceServiceImpl();
                jobPreferenceService.setTransaction(transaction);

                ValidationCommand validationCommand = new JobPreferenceValidationCommand();
                if (!validationCommand.isValid(request, response)) {
                    request.setAttribute("specializations", jobPreferenceService.findAllSpecializations());
                    request.setAttribute("currencies", Currency.values());
                    request.setAttribute("schedules", Schedule.values());
                    request.getServletContext().getRequestDispatcher(PagePath.JOB_PREFERENCE).forward(request, response);
                } else {

                    JobPreferenceBuilder builder = new JobPreferenceBuilder();
                    JobPreference preference = builder.build(request);
                    preference.getSpecialization()
                            .setId(jobPreferenceService.findIdBySpecialization(preference.getSpecialization().getName()));

                    if (Objects.nonNull(preference.getId())) {
                        jobPreferenceService.update(preference);
                    } else {
                        Integer preferenceId = jobPreferenceService.addNewEntity(preference);
                        preference.setId(preferenceId);

                        ResumeService resumeService = new ResumeServiceImpl();
                        resumeService.setTransaction(transaction);
                        Optional<Resume> resume = resumeService.findResumeByUserId(userId);
                        resume.get().setJobPreference(preference);
                        resumeService.createJobPreference(resume.orElseThrow(DaoException::new));

                    }

                    transaction.commit();
                    response.sendRedirect(request.getContextPath() + UriPattern.EMPLOYEE_HOME.getUrl());
                }
            }

        } catch (InsertIdDataBaseException | DaoException | PoolException e) {
            transaction.rollback();
            log.error(e);
            response.sendError(500);
        } finally {
            transaction.close();
        }
    }
}


