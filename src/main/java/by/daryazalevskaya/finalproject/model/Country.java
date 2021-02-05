package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * {@code Country} class creates object-wrapper from country catalog
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Country extends Entity {

    /**
     * Instantiates a new Country.
     *
     * @param id   country id
     * @param name country name
     */
    public Country(Integer id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Instantiates a new Country.
     *
     * @param name country name
     */
    public Country(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new Country.
     *
     * @param id language id
     */
    public Country(Integer id) {
        this.id=id;
    }

    private String name;


}
