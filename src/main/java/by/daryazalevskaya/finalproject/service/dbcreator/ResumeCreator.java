package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResumeCreator extends Creator<Resume> {
    @Override
    public Resume createEntity(ResultSet set) throws SQLException {
        return Resume.builder()
                .contact(new Contact(wasNull(set,"contact_id")))
                .description(set.getString("prof_description"))
                .jobPreference(new JobPreference(wasNull(set, "job_preference_id")))
                .personalInfo(new EmployeePersonalInfo((wasNull(set, "personal_info_id"))))
                .user(new User(wasNull(set, "usr_id")))
                .build();

    }

    private Integer wasNull(ResultSet resultSet, String columnName) throws SQLException {
        Integer value=resultSet.getInt(columnName);
        if (resultSet.wasNull()) {
             value=null;
        }
        return value;
    }
}
