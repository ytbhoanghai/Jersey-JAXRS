package com.nguyen.serviceImpl;

import com.nguyen.exception.UserNotFoundException;
import com.nguyen.model.Role;
import com.nguyen.model.User;
import com.nguyen.service.AuthenticationService;
import com.nguyen.util.JWTHelper;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("/authentication")
public class AuthenticationServiceImpl implements AuthenticationService {

    private static List<User> userList;
    static {
        userList = Arrays.asList(
                new User("user", "123", Collections.singletonList(Role.USER)),
                new User("admin", "123", Collections.singletonList(Role.ADMIN)));
    }

    @Override
    public Response doPostLogin(String username, String password) {
        User user = userList.stream()
                .filter(_user -> _user.getUsername().equals(username) && _user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new UserNotFoundException(UserNotFoundException.class.getSimpleName());
        }
        String token = JWTHelper.createJWT(user);

        return Response.ok(token).build();
    }
}
