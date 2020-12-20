package by.daryazalevskaya.finalproject.dao;

import by.daryazalevskaya.finalproject.model.User;

public interface UserDao extends Dao<User> {
    User read(String username);
}
