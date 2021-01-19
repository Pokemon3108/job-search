package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Vacancy extends Entity {

    public Vacancy(Integer id) {
        super.id=id;
    }

    private String city;
    private Schedule schedule;
    private String duties;
    private String requirements;
    private Employer employer;
    private String position;
    private int salary;
    private Currency currency;
}
