package by.daryazalevskaya.finalproject.service.dbcreator;

import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;
import by.daryazalevskaya.finalproject.model.type.LanguageLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type EmployeeLanguage creator is used for creation {@EmployeeLanguage Country} object from sql result set
 * {@link by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage}
 */
public class EmployeeLanguageCreator extends Creator<EmployeeLanguage> {
    @Override
    public EmployeeLanguage createEntity(ResultSet set) throws SQLException {
        return  EmployeeLanguage.builder()
                .level(LanguageLevel.valueOf(set.getString("level")))
                .name(new Language(set.getInt("language_id")))
                .build();
    }
}
