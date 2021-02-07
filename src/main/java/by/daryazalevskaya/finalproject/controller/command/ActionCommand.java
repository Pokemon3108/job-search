package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import lombok.Setter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Setter
public abstract class ActionCommand {
    protected ServiceFactory serviceFactory;

    public  abstract void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
