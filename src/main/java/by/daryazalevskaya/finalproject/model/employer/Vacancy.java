package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.Position;
import by.daryazalevskaya.finalproject.model.type.Currency;
import by.daryazalevskaya.finalproject.model.type.Schedule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Vacancy extends Entity {
    private String city;
    private int minExperience;
    private Schedule schedule;
    private String duties;
    private String requirements;
    private Employer employer;
    private Position position;
    private int salary;
    private Currency currency;
}
