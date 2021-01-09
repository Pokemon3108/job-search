package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.Dao;
import by.daryazalevskaya.finalproject.dao.UserDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.IllegalOperationException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserServiceImpl extends UserService {

    @Override
    public boolean addNewEntity(User entity) throws DaoException, InsertIdDataBaseException {
        boolean isAdded = false;
        UserDaoImpl userDao = new UserDaoImpl();

        if (userDao.read(entity.getUsername()).isEmpty()) {
            entity.setPassword(crypt(entity.getPassword()));
            userDao.create(entity);
            isAdded = true;
        }
        return isAdded;
}


    @Override
    public Optional<User> read(int id) throws DaoException {
        Optional<User> user = Optional.empty();
        try {
            UserDao userDao = new UserDaoImpl();
            user = userDao.read(id);
        } catch (IllegalOperationException ex) {
            //can't be thrown in this method
        }
        return user;
    }

    @Override
    public void update(User entity) throws DaoException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            userDao.update(entity);
        } catch (IllegalOperationException ex) {
            //can't be thrown in this method
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Dao<User> userDao = new UserDaoImpl();
            userDao.delete(id);
        } catch (IllegalOperationException ex) {
            //can't be thrown in this method
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            Dao<User> userDao = new UserDaoImpl();
            users = userDao.findAll();
        } catch (DaoException | IllegalOperationException e) {
            log.error(e);
        }
        return users;
    }

    private String crypt(String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("sha-256");
            digest.reset();
            digest.update(string.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String hashStr = formatter.toString();
            formatter.close();
            return hashStr;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
