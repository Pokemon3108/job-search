package by.daryazalevskaya.finalproject.model;

import by.daryazalevskaya.finalproject.model.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) // object id will be included
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Entity {

    public User(int id) {
        super.id=id;
    }

    protected String email;
    protected String password;
    protected Role role;


}
