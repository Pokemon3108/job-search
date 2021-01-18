package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Language extends Entity {
    public Language(Integer id, String name) {
        super.id=id;
        this.name=name;
    }

    public Language(Integer id) {
        super.id=id;
    }


    private String name;

}

