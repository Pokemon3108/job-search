package by.daryazalevskaya.finalproject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // object id will be included
@Data
public class Contact extends Entity {
    private String telephone;
    private String email;
    private String skype;
}
