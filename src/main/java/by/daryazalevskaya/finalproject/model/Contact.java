package by.daryazalevskaya.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // object id will be included
@Data
@SuperBuilder
public class Contact extends Entity {
    private String telephone;
    private String email;
    private String skype;
}
