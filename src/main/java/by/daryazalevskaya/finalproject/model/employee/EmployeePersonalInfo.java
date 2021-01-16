package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class EmployeePersonalInfo extends Entity {

    public EmployeePersonalInfo(Integer id) {
        super.id=id;
    }

    private String name;
    private String surname;
    private LocalDate birthday;
    private Gender gender;
    private Country country;
    private String city;
}
