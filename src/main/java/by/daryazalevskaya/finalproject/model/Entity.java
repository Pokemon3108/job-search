package by.daryazalevskaya.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


/**
 * The base type for entities from database.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Entity implements Serializable {
    /**
     * The Id.
     */
    protected Integer id;
}
