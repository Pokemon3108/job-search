package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * {@code Language} class creates object-wrapper from catalog language
 */
@SuperBuilder
@Getter
@Setter
public class Language extends Entity {
    /**
     * Instantiates a new Language.
     *
     * @param id   language id
     * @param name language name
     */
    public Language(Integer id, String name) {
        super.id=id;
        this.name=name;
    }

    /**
     * Instantiates a new Language.
     *
     * @param id language id
     */
    public Language(Integer id) {
        super.id=id;
    }


    private String name;

}

