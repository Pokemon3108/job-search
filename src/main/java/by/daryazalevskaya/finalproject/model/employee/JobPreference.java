package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
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

    private Integer salary;
    private Currency currency;
    private Specialization specialization;
    private Schedule schedule;
    private Integer experience;
    private String position;
}
