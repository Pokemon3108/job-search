package by.daryazalevskaya.finalproject.model.employee;

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
public class JobPreference extends Entity {

    public JobPreference(Integer id) {
        super.id=id;
    }

    private int salary;
    private Currency currency;
    private String specialization;
    private Schedule schedule;
    private int experience;
    private Position desiredPosition;
}
