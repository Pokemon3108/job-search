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
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
public class UserServiceImpl extends UserService {

    @Override
    public Integer addNewEntity(User entity) throws DaoException, InsertIdDataBaseException {
        Integer id=null;
        UserDao userDao = transaction.createDao(DaoType.USER);

        if (userDao.read(entity.getEmail()).isEmpty()) {
            entity.setPassword(crypt(entity.getPassword()));
            id = userDao.create(entity);
            entity.setId(id);

        }
        return id;
    }


    @Override
    public Optional<User> read(int id) throws DaoException {
        Optional<User> user;
        UserDao userDao = transaction.createDao(DaoType.USER);
        user = userDao.read(id);
        return user;
    }

    @Override
    public void update(User entity) throws DaoException {
        UserDao userDao = transaction.createDao(DaoType.USER);
        userDao.update(entity);
    }

    @Override
    public void delete(int id) throws DaoException {
        UserDao userDao = transaction.createDao(DaoType.USER);
        userDao.delete(id);
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users;
        UserDao userDao = new UserDaoImpl();
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

    @Override
    public boolean isValidLoginAndPassword(String email, String password) throws DaoException {
        AtomicBoolean isValid = new AtomicBoolean(true);
        UserDao userDao = transaction.createDao(DaoType.USER);
        Optional<User> user = userDao.read(email);

        user.ifPresentOrElse((user1 -> isValid.set(user1.getPassword().equals(crypt(password)))),
                () -> isValid.set(false));

        return isValid.get();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        UserDao userDao = transaction.createDao(DaoType.USER);
        return userDao.read(email);
    }
}
