package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.service.VacancyComplicatedService;
import by.daryazalevskaya.finalproject.service.VacancyService;

public class VacancyComplicatedServiceImpl extends VacancyComplicatedService {
    @Override
    public void deleteVacancy(Integer vacancyId) throws DaoException, TransactionException {
        VacancyService vacancyService=new VacancyServiceImpl();
        vacancyService.setTransaction(transaction);
        vacancyService.deleteVacancyFromEmployeeVacancies(vacancyId);
        vacancyService.delete(vacancyId);
        transaction.commit();
    }
}
