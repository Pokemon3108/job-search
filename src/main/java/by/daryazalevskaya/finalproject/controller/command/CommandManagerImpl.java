package by.daryazalevskaya.finalproject.controller.command;

import by.daryazalevskaya.finalproject.dao.exception.ConnectionException;
import by.daryazalevskaya.finalproject.dao.exception.TransactionException;
import by.daryazalevskaya.finalproject.service.factory.ServiceFactory;
import lombok.AllArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class CommandManagerImpl implements CommandManager {

    private ServiceFactory factory;

    @Override
    public void execute(ActionCommand command, HttpServletRequest request, HttpServletResponse response) throws ConnectionException, TransactionException, ServletException, IOException {
        command.setServiceFactory(factory);
        command.execute(request, response);
    }

    @Override
    public void close() throws TransactionException {
        factory.close();
    }
}
