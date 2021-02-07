package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class Resume extends Entity {

    /**
     * Instantiates a new Resume.
     *
     * @param id
     */
    public Resume(Integer id) {
        super.id=id;
    }

    private LocalDate update;
    private String skills;
    private Employee employee;
    private Contact contact;
    private EmployeePersonalInfo personalInfo;
    private JobPreference jobPreference;
    private EmployeeLanguage language;
}
