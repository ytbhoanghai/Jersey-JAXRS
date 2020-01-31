package com.nguyen.serviceImpl;

import com.nguyen.exception.UsernameNotFoundException;
import com.nguyen.helper.JWTHelper;
import com.nguyen.model.User;
import com.nguyen.repository.UserRepository;
import com.nguyen.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Response authenticationUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new UsernameNotFoundException("username not found exception");
        }
        String token = JWTHelper.createJWT(user);
        return Response.ok(token).build();
    }
}
