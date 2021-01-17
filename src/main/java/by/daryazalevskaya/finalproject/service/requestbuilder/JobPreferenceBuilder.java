package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import javax.servlet.http.HttpServletRequest;

public class JobPreferenceBuilder implements RequestBuilder<JobPreference> {
    @Override
    public JobPreference build(HttpServletRequest request) {
        String position = request.getParameter("position");
        String specializationName = request.getParameter("specialization");
        String schedule1=request.getParameter("schedule");
        Schedule schedule = Schedule.valueOf(request.getParameter("schedule"));
        Currency currency=Currency.valueOf(request.getParameter("currency"));

        JobPreference jobPreference=JobPreference
                .builder()
                .position(position)
                .schedule(schedule)
                .specialization(new Specialization(specializationName))
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
