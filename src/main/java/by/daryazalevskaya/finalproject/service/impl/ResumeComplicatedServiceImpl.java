package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.model.Contact;
import by.daryazalevskaya.finalproject.model.employee.EmployeeLanguage;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.employee.JobPreference;
import by.daryazalevskaya.finalproject.model.employee.Resume;
import by.daryazalevskaya.finalproject.service.ContactService;
import by.daryazalevskaya.finalproject.service.EmployeeLanguageService;
import by.daryazalevskaya.finalproject.service.EmployeePersonalInfoService;
import by.daryazalevskaya.finalproject.service.JobPreferenceService;
import by.daryazalevskaya.finalproject.service.ResumeComplicatedService;
import by.daryazalevskaya.finalproject.service.ResumeService;

import java.util.Objects;
import java.util.Optional;

public class ResumeComplicatedServiceImpl extends ResumeComplicatedService {
    public void saveEmployeePersonalInfo(Integer userId, JobPreference preference) throws  TransactionException, DaoException {
        JobPreferenceService jobPreferenceService=new JobPreferenceServiceImpl();
        jobPreferenceService.setTransaction(transaction);
        if (Objects.nonNull(preference.getId())) {
            jobPreferenceService.update(preference);
        } else {
            Integer preferenceId = jobPreferenceService.createJobPreference(preference);
            preference.setId(preferenceId);

            ResumeService resumeService = new ResumeServiceImpl();
            resumeService.setTransaction(transaction);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            resumeService.createJobPreference(resume.orElseThrow(DaoException::new), preference);

        }
        transaction.commit();
    }

    public void saveEmployeeLanguage(Integer userId, EmployeeLanguage language) throws TransactionException, DaoException {
        EmployeeLanguageService employeeLanguageService=new EmployeeLanguageServiceImpl();
        employeeLanguageService.setTransaction(transaction);
        if (Objects.nonNull(language.getId())) {
            employeeLanguageService.update(language);
        } else {
            Integer languageId = employeeLanguageService.createLanguage(language);
            language.setId(languageId);

            ResumeService resumeService = new ResumeServiceImpl();
            resumeService.setTransaction(transaction);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            if (resume.isPresent()) {
                resumeService.createLanguage(resume.get(), language);
            }
        }
        transaction.commit();
    }

    @Override
    public void saveEmployeePersonalInfo(Integer userId, EmployeePersonalInfo info) throws TransactionException, DaoException {
        EmployeePersonalInfoService infoService=new EmployeePersonalInfoServiceImpl();
        infoService.setTransaction(transaction);
        if (Objects.nonNull(info.getId())) {
            infoService.update(info);
        } else {
            Integer infoId = infoService.createPersonalInfo(info);
            info.setId(infoId);

            ResumeService resumeService = new ResumeServiceImpl();
            resumeService.setTransaction(transaction);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            resumeService.createPersonalInfo(resume.orElseThrow(DaoException::new), info);

        }
        transaction.commit();
    }

    @Override
    public void saveContact(Integer userId, Contact contact) throws DaoException, TransactionException {
        ContactService contactService=new ContactServiceImpl();
        contactService.setTransaction(transaction);
        if (Objects.nonNull(contact.getId())) {
            contactService.update(contact);
        } else {
            Integer contactId = contactService.createContact(contact);
            contact.setId(contactId);

            ResumeService resumeService = new ResumeServiceImpl();
            resumeService.setTransaction(transaction);
            Optional<Resume> resume = resumeService.findResumeByUserId(userId);
            resumeService.createContact(resume.orElseThrow(DaoException::new), contact);
        }
        transaction.commit();
    }
}
