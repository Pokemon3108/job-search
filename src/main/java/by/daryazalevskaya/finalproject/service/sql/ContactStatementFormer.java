package by.daryazalevskaya.finalproject.service.sql;

import by.daryazalevskaya.finalproject.model.Contact;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactStatementFormer extends StatementFormer<Contact>{

    @Override
    public void setStatement(PreparedStatement statement, Contact entity) throws SQLException {
        statement.setString(1, entity.getTelephone());
        statement.setString(2, entity.getEmail());
        statement.setString(3, entity.getSkype());
    }
}