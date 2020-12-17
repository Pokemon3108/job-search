package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeePersonalInfo extends Entity {
    private String name;
    private String surname;
    private LocalDate birthday;
    private Gender gender;
    private String country;
    private String city;
}
