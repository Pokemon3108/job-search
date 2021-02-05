package by.daryazalevskaya.finalproject.model;

import by.daryazalevskaya.finalproject.model.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * The type User.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Entity {

    /**
     * Instantiates a new User.
     *
     * @param id the id
     */
    public User(int id) {
        super.id=id;
    }

    protected String email;

    protected String password;

    protected Role role;


}
