package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type ResumeCreator creator is used for creation {@code Resume} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employee.Resume}
 */
public class ResumeCreator extends Creator<Resume> {
    @Override
    public Resume createEntity(ResultSet set) throws SQLException {
        Resume resume= Resume.builder()
                .contact(new Contact(wasNullInt(set,"contact_id")))
                .skills(set.getString("prof_description"))
                .jobPreference(new JobPreference(wasNullInt(set, "job_preference_id")))
                .personalInfo(new EmployeePersonalInfo((wasNullInt(set, "personal_info_id"))))
                .employee(new Employee(wasNullInt(set, "usr_id")))
                .language(new EmployeeLanguage(wasNullInt(set, "language_id")))
                .build();
        if (existsColumn(set, "id")) {
            resume.setId(set.getInt("id"));
        }
        return resume;

    }

}
