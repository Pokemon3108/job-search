package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class EmployeeLanguage extends Entity {

    public EmployeeLanguage(Integer id) {
        super.id=id;
    }

    public EmployeeLanguage(Integer id, LanguageLevel level) {
        super.id=id;
        this.level=level;
    }

    private Language name;
    private LanguageLevel level;
}
