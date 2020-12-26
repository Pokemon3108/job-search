package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Country extends Entity {
    public Country(int id) {
        super.id = id;
    }

    public Country(int id, String countryName) {
        super.id = id;
        this.name = countryName;
    }

    private String name;


}
