package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class JobPreferenceCreator extends Creator<JobPreference> {

    public JobPreferenceCreator(Function<Integer, String> findFieldByForeignKey) {
        super.findFieldByForeignKey = findFieldByForeignKey;
    }

    @Override
    public JobPreference createEntity(ResultSet set) throws SQLException {
        return JobPreference.builder()
                .desiredPosition(set.getString("desired_position"))
                .salary(set.getInt("salary"))
                .experience(set.getInt("experience"))
                .currency(Currency.valueOf(set.getString("currency")))
                .schedule(Schedule.valueOf(set.getString("schedule")))
                .specialization(findFieldByForeignKey.apply(set.getInt("specialization_id")))
                .build();
    }
}
