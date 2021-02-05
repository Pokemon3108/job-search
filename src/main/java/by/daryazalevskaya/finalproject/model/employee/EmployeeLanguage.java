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

    /**
     * Instantiates a new Employee language.
     *
     * @param id
     */
    public EmployeeLanguage(Integer id) {
        super.id=id;
    }


    /**
     * Instantiates a new Employee language.
     *
     * @param name  the language name from language catalog
     * @param level the language level
     */
    public EmployeeLanguage(Language name, LanguageLevel level) {
        this.name=name;
        this.level=level;
    }

    private Language name;
    private LanguageLevel level;
}
