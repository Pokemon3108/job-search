package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Specialization extends Entity {
    public Specialization(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Specialization(String name) {
        this.name = name;
    }

    public Specialization(Integer id) {
        this.id=id;
    }

    private String name;
}
