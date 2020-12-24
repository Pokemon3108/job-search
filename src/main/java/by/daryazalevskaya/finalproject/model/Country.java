package by.daryazalevskaya.finalproject.model;

import lombok.Data;

@Data
public class Country extends Entity {
    public Country(int id) {
        super.id=id;
    }

    private String name;
}
