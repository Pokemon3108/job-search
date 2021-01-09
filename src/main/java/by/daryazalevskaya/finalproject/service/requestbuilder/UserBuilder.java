package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.User;
import by.daryazalevskaya.finalproject.model.type.Role;

import javax.servlet.http.HttpServletRequest;

public class UserBuilder implements RequestBuilder<User> {

    @Override
    public User build(HttpServletRequest request) {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Role role=Role.valueOf(request.getParameter("role"));
        return User.builder()
                .username(username)
                .role(role)
                .password(password)
                .build();
    }
}
