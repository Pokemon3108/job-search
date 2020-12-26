package by.daryazalevskaya.finalproject.model;

import by.daryazalevskaya.finalproject.model.type.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true) // object id will be included
@Data
@SuperBuilder
@NoArgsConstructor
public class User extends Entity {

    public User(int id) {
        super.id=id;
    }

    private String username;
    private String password;
    private Role role;
}
