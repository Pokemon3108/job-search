package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Entity implements Serializable {
    protected int id;
}
