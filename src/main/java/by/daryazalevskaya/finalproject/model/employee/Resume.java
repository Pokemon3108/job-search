package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Resume extends Entity {

    public Resume(int id) {
        super.id=id;
    }

    private LocalDate update;
    private String description;
    private User user;
    private Contact contact;
    private EmployeePersonalInfo personalInfo;
    private JobPreference jobPreference;
    private List<Language> languageList;
}
