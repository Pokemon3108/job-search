package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.Language;

import java.util.List;

public abstract class EmployeeLanguageService extends BaseService<EmployeeLanguage> {
    public abstract List<Language> findAllLanguages() throws DaoException;
}
