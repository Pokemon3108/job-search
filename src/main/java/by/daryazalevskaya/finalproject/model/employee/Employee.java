package by.daryazalevskaya.finalproject.model.employee;

import by.daryazalevskaya.finalproject.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Employee extends User {

    public Employee(Integer id) {
        super(id);
    }

    // private User user;
    private Resume resume;
}
