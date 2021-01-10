package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActionCommand {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionException;
}
