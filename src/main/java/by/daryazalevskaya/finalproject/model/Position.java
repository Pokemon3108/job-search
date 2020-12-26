package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Position extends Entity {
    public Position(int id) {
        super.id = id;
    }

    public Position(int id, String countryName) {
        super.id = id;
        this.name = countryName;
    }

    private String name;

}