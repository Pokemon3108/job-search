package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPreferenceCreator extends Creator<JobPreference> {

    @Override
    public JobPreference createEntity(ResultSet set) throws SQLException {
        return JobPreference.builder()
                .position(set.getString("position"))
                .salary(set.getInt("salary"))
                .experience(set.getInt("experience"))
                .currency(Currency.valueOf(set.getString("currency")))
                .schedule(Schedule.valueOf(set.getString("schedule")))
                .specialization(new Specialization(set.getInt("specialization_id")))
                .build();
    }
}
