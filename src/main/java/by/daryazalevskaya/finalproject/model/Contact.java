package by.daryazalevskaya.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // object id will be included
@Data
public class Contact extends Entity {
    private String telephone;
    private String email;
    private String skype;
}
