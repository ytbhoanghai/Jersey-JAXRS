package com.nguyen.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface AuthenticationService {

    @POST @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    Response doPostLogin(@QueryParam("username") String username, @QueryParam("password") String password);
}
