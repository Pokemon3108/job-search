package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.PoolException;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Specialization;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;

import java.util.List;
import java.util.Optional;

public class JobPreferenceServiceImpl extends JobPreferenceService {

    @Override
    public Integer addNewEntity(JobPreference entity) throws DaoException, InsertIdDataBaseException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.create(entity);
    }

    @Override
    public Optional<JobPreference> read(Integer id) throws DaoException, PoolException {
        if (id==null) {
            return Optional.empty();
        }
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        Optional<JobPreference> preference = jobPreferenceDao.read(id);
        if (preference.isPresent()) {
            Optional<Specialization> specialization = findSpecializationById(preference.get().getSpecialization().getId());
            specialization.ifPresent(spec -> preference.get().setSpecialization(spec));
        }
        return preference;
    }

    @Override
    public void update(JobPreference entity) throws DaoException, PoolException, InsertIdDataBaseException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        jobPreferenceDao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException, PoolException {

    }

    @Override
    public List<JobPreference> findAll() throws DaoException, PoolException {
        return null;
    }

    @Override
    public Integer findIdBySpecialization(String specialization) throws DaoException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.findIdBySpecialization(specialization);
    }

    @Override
    public Optional<Specialization> findSpecializationById(int id) throws DaoException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.findSpecializationById(id);
    }

    @Override
    public List<Specialization> findAllSpecializations() throws DaoException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.findAllSpecializations();
    }
}
