package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class Employer extends Entity {
    private User user;
    private String companyName;
    private String country;
    private String city;
    private Contact contact;
}
