package com.nguyen.filter;

import com.nguyen.implService.TextServiceImpl;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Authorization
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if (!hasToken(containerRequestContext)) {
            Response response = Response.status(
                    Response.Status.UNAUTHORIZED).entity("User cannot access the resource").build();

            containerRequestContext.abortWith(response);
        }
    }

    private boolean hasToken(ContainerRequestContext containerRequestContext) {
        String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("ytb")) {
            return false;
        }

        token = token.substring("ytb".length());
        return TextServiceImpl.existToken(token);
    }
}
