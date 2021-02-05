package by.daryazalevskaya.finalproject.model.employer;

import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.Entity;
import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class Employer extends User {

    /**
     * Instantiates a new Employer.
     *
     * @param id
     */
    public Employer(Integer id) {
        super.id=id;
    }

    private String companyName;
    private Country country;
    private String city;
    private Contact contact;
}
