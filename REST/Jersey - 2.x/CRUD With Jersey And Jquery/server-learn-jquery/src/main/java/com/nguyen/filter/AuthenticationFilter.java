package com.nguyen.filter;

import com.nguyen.model.BasicSecurityContext;
import com.nguyen.model.User;
import com.nguyen.util.JWTHelper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEMA = "bearer";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (!isTokenBaseAuthentication(token)) {
            System.out.println("No Token");
            return;
        }

        token = token.substring(AUTHENTICATION_SCHEMA.length() + 1);
        User user = JWTHelper.getUserFormToken(token);

        System.out.println(user);
        SecurityContext securityContext = containerRequestContext.getSecurityContext();
        containerRequestContext.setSecurityContext(new BasicSecurityContext(user, securityContext.isSecure()));
    }

    private boolean isTokenBaseAuthentication(String jwt) {
        return jwt != null && jwt.startsWith(AUTHENTICATION_SCHEMA + " ");
    }
}
