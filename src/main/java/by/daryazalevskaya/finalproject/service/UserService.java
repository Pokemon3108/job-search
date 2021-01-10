package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.model.User;

public abstract class UserService extends BaseService<User> {
    public UserService() throws ConnectionException {
        super();
    }
    // protected abstract boolean exists(String username);

}
