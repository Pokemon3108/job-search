package by.daryazalevskaya.finalproject.service.creator;

import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageCreator extends Creator<Language> {
    @Override
    public Language createEntity(ResultSet set) throws SQLException {
        return Language.builder()
                .level(LanguageLevel.valueOf(set.getString("level")))
                .name(set.getString("name"))
                .build();
    }
}
