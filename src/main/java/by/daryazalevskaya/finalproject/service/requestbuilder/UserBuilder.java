package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import javax.servlet.http.HttpServletRequest;

public class UserBuilder implements RequestBuilder {

    @Override
    public User build(HttpServletRequest request) {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        Role role=Role.valueOf(request.getParameter("role"));
        return User.builder()
                .email(email)
                .role(role)
                .password(password)
                .build();
    }
}
