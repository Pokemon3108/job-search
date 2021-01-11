package by.daryazalevskaya.finalproject.service.impl;

import by.daryazalevskaya.finalproject.dao.Dao;
import by.daryazalevskaya.finalproject.dao.DaoType;
import by.daryazalevskaya.finalproject.dao.UserDao;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.InsertIdDataBaseException;
import by.daryazalevskaya.finalproject.dao.impl.UserDaoImpl;
import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Log4j2
public class UserServiceImpl extends UserService {

    @Override
    public boolean addNewEntity(User entity) throws DaoException, InsertIdDataBaseException {
        boolean isAdded = false;
        UserDao userDao=transaction.createDao(DaoType.USER);

        if (userDao.read(entity.getEmail()).isEmpty()) {
            entity.setPassword(crypt(entity.getPassword()));
            entity.setId(userDao.create(entity));
            isAdded = true;
        }
        return isAdded;
    }


    @Override
    public Optional<User> read(int id) throws DaoException {
        Optional<User> user;
        UserDao userDao = new UserDaoImpl();
        user = userDao.read(id);
        return user;
    }

    @Override
    public void update(User entity) throws DaoException {
        Dao<User> userDao = new UserDaoImpl();
        userDao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException {
        Dao<User> userDao = new UserDaoImpl();
        userDao.delete(id);
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users;
        Dao<User> userDao = new UserDaoImpl();
        users = userDao.findAll();
        return users;
    }

    private String crypt(String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("sha-256");
            digest.reset();
            digest.update(string.getBytes());
            byte[] hash = digest.digest();
            Formatter formatter = new Formatter();
            for (byte b : hash) {
                formatter.format("%02X", b);
            }
            String hashStr = formatter.toString();
            formatter.close();
            return hashStr;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
