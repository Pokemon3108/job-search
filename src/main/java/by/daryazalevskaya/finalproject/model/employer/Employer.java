package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Employer extends Entity {

    public Employer(int id) {
        super.id=id;
    }

    private User user;
    private String companyName;
    private Country country;
    private String city;
    private Contact contact;
}
