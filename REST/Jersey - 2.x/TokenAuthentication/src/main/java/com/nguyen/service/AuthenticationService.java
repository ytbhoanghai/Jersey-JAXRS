package com.nguyen.service;

import com.nguyen.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface AuthenticationService {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Response authenticationUser(@FormParam("username") String username,
                                @FormParam("password") String password);
}
