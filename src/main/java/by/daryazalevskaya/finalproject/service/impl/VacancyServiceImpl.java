package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.VacancyDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employer.Vacancy;
import by.daryazalevskaya.finalproject.service.VacancyService;

import java.util.List;
import java.util.Optional;

public class VacancyServiceImpl extends VacancyService {
    @Override
    public List<Vacancy> findVacanciesByEmployerId(int id) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        return dao.findVacanciesByEmployerId(id);
    }

    @Override
    public void deleteVacancyFromEmployeeVacancies(int vacancyId) throws DaoException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        dao.deleteVacancyFromEmployeeVacancies(vacancyId);
    }

    @Override
    public Integer addNewEntity(Vacancy entity) throws DaoException, InsertIdDataBaseException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        return dao.create(entity);
    }

    @Override
    public Optional<Vacancy> read(Integer id) throws DaoException, PoolException {
        if (id == null) {
            return Optional.empty();
        }
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        return dao.read(id);
    }

    @Override
    public void update(Vacancy entity) throws DaoException, PoolException, InsertIdDataBaseException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        dao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {
        VacancyDao dao = transaction.createDao(DaoType.VACANCY);
        dao.delete(id);
    }

    @Override
    public List<Vacancy> findAll() throws DaoException, PoolException {
        return null;
    }
}
