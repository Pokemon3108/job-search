package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public abstract class EmployeePersonalInfoService extends BaseService<EmployeePersonalInfo> {
    public abstract List<Country> getCountries() throws DaoException;
    public Integer countAge(LocalDate birthday) {
        LocalDate today=LocalDate.now();
        return Period.between(birthday, today).getYears();
    }

}
