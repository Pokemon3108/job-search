package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactCreator extends Creator<Contact> {
    @Override
    public Contact createEntity(ResultSet set) throws SQLException {
        return Contact.builder()
                .id(set.getInt("id"))
                .telephone(set.getString("telephone"))
                .email(set.getString("email"))
                .skype(set.getString("skype"))
                .build();

    }
}
