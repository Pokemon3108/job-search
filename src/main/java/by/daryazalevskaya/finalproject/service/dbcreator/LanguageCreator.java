package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employee.Language;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageCreator extends Creator<Language> {
    @Override
    public Language createEntity(ResultSet set) throws SQLException {
        return Language.builder()
                .name(set.getString("name"))
                .id(set.getInt("id"))
                .build();
    }
}
