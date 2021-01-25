package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
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
                .skills(set.getString("prof_description"))
                .jobPreference(new JobPreference(wasNull(set, "job_preference_id")))
                .personalInfo(new EmployeePersonalInfo((wasNull(set, "personal_info_id"))))
                .user(new User(wasNull(set, "usr_id")))
                .language(new EmployeeLanguage(wasNull(set, "language_id")))
                .build();

    }

}
