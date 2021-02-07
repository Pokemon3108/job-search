package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import javax.servlet.http.HttpServletRequest;

/**
 * The  JobPreferenceBuilder is used for creation {@code JobPreference} object from httpRequest
 * {@link by.daryazalevskaya.finalproject.model.employee.JobPreference}
 */
public class JobPreferenceBuilder implements RequestBuilder<JobPreference> {
    @Override
    public JobPreference build(HttpServletRequest request) {
        String position = request.getParameter("position");
        Integer specialization = Integer.parseInt(request.getParameter("specialization"));

        Schedule schedule = Schedule.valueOf(request.getParameter("schedule"));
        Currency currency=Currency.valueOf(request.getParameter("currency"));

        JobPreference jobPreference=JobPreference
                .builder()
                .position(position)
                .schedule(schedule)
                .specialization(new Specialization(specialization))
                .currency(currency)
                .build();

        if (!request.getParameter("salary").isEmpty()) {
            jobPreference.setSalary(Integer.parseInt(request.getParameter("salary")));
        }

        if (!request.getParameter("experience").isEmpty()) {
            jobPreference.setExperience(Integer.parseInt(request.getParameter("experience")));
        }

        return jobPreference;

    }
}
