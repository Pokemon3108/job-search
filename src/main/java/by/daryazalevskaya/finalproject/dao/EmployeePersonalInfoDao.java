package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;

public interface EmployeePersonalInfoDao {
    Integer findIdByCountry(String country) throws DaoException;
    String findCountryById(int id) throws DaoException;
}
