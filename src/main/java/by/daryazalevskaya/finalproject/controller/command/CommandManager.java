package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandManager {
    void execute(ActionCommand command, HttpServletRequest request, HttpServletResponse response) throws ConnectionException, TransactionException, ServletException, IOException;

    void close() throws TransactionException;
}
