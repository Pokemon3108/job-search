package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Vacancy extends Entity {
    private String city;
    private int minExperience;
    private Schedule schedule;
    private String duties;
    private String requirements;
    private Employer employer;
    private String position;
    private int salary;
    private Currency currency;
}
