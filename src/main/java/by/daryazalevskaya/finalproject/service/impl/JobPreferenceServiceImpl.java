package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.JobPreferenceDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Specialization;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.type.DaoType;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;

import java.util.List;
import java.util.Optional;

public class JobPreferenceServiceImpl extends JobPreferenceService {

    @Override
    public Integer createJobPreference(JobPreference entity) throws DaoException, TransactionException {
        try {
            JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
            return jobPreferenceDao.create(entity);
        } catch (DaoException | InsertIdDataBaseException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<JobPreference> read(Integer id) throws DaoException {
        if (id == null) {
            return Optional.empty();
        }
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        Optional<JobPreference> preference = jobPreferenceDao.read(id);
        if (preference.isPresent()) {
            Optional<Specialization> specialization = readSpecializationById(preference.get().getSpecialization().getId());
            specialization.ifPresent(spec -> preference.get().setSpecialization(spec));
        }
        return preference;
    }

    @Override
    public void update(JobPreference entity) throws DaoException, TransactionException {
        try {
            JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
            jobPreferenceDao.update(entity);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException, TransactionException {
        try {
            JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
            jobPreferenceDao.delete(id);
        } catch (DaoException ex) {
            transaction.rollback();
            throw new DaoException(ex);
        }
    }


    @Override
    public Integer readIdBySpecializationName(String specialization) throws DaoException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.readIdBySpecialization(specialization);
    }

    @Override
    public Optional<Specialization> readSpecializationById(Integer id) throws DaoException {
        if (id==null) {
            return Optional.empty();
        }
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.readSpecializationById(id);
    }

    @Override
    public List<Specialization> findAllSpecializations() throws DaoException {
        JobPreferenceDao jobPreferenceDao = transaction.createDao(DaoType.JOB_PREFERENCE);
        return jobPreferenceDao.readAllSpecializations();
    }
}
