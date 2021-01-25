package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;

public abstract class VacancyComplicatedService extends BaseService {
    public abstract void deleteVacancy(Integer vacancyId) throws DaoException, TransactionException;
}
