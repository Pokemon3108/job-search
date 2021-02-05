package by.daryazalevskaya.finalproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Contact extends Entity {

    /**
     * Instantiates a new Contact.
     *
     * @param id
     */
    public Contact(Integer id) {
        super.id=id;
    }

    private String telephone;
    private String email;
    private String skype;
}
