package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Resume extends Entity {
    private LocalDate update;
    private String description;
    private User user;
    private Contact contact;
    private EmployeePersonalInfo personalInfo;
    private JobPreference jobPreference;
}
