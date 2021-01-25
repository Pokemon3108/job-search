package by.daryazalevskaya.finalproject.controller.command.validation;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.DaoException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidationCommand {
    boolean isValid(HttpServletRequest request, HttpServletResponse response) throws DaoException, TransactionException;
}
