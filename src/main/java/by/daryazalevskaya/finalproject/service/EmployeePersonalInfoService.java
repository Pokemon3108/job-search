package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;

import java.util.List;

public abstract class EmployeePersonalInfoService extends BaseService<EmployeePersonalInfo> {
    public abstract List<Country> getCountries() throws DaoException;
}
