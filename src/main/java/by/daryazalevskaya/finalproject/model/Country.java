package by.daryazalevskaya.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Country extends Entity {

    public Country(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(Integer id) {
        this.id=id;
    }

    private String name;


}
