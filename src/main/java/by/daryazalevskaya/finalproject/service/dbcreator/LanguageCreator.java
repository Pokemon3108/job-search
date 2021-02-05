package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employee.Language;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type LanguageCreator creator is used for creation {@code Language} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employee.Language}
 */
public class LanguageCreator extends Creator<Language> {
    @Override
    public Language createEntity(ResultSet set) throws SQLException {
        return Language.builder()
                .name(set.getString("name"))
                .id(set.getInt("id"))
                .build();
    }
}
