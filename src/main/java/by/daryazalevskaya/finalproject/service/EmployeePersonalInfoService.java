package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

public abstract class EmployeePersonalInfoService extends BaseService {
    public abstract List<Country> getCountries() throws DaoException;

    public abstract Integer addNewPersonalInfo(EmployeePersonalInfo info) throws DaoException, TransactionException;

    public abstract Optional<EmployeePersonalInfo> read(Integer id) throws DaoException;

    public abstract void update(EmployeePersonalInfo employeePersonalInfo) throws DaoException, TransactionException;

    public abstract void delete(int id) throws DaoException, TransactionException;


    public Integer countAge(LocalDate birthday) {
        LocalDate today=LocalDate.now();
        return Period.between(birthday, today).getYears();
    }

}
