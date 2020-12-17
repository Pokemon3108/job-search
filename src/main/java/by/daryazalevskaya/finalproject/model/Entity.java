package by.daryazalevskaya.finalproject.model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Entity implements Serializable {
    private int id;
}
