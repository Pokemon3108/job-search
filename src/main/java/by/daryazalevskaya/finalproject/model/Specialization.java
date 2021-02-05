package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * {@code Specialization} class creates object-wrapper from specialization catalog
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Specialization extends Entity {
    /**
     * Instantiates a new Specialization.
     *
     * @param id   specialization id
     * @param name specialization name
     */
    public Specialization(Integer id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Instantiates a new Specialization.
     *
     * @param name specialization name
     */
    public Specialization(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Specialization.
     *
     * @param id specialization id
     */
    public Specialization(Integer id) {
        this.id=id;
    }

    private String name;
}
