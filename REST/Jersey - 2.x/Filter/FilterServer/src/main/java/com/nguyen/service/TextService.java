package com.nguyen.service;

import com.nguyen.entity.Text;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface TextService {

    // CRUD
    @POST
    @Consumes(MediaType.APPLICATION_JSON) @Produces({MediaType.APPLICATION_JSON})
    Response insert(Text text);


    @GET @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Response selectById(@PathParam(value = "id") Integer id);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Response selectAll();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON) @Produces({MediaType.APPLICATION_JSON})
    Response update(Text text);

    @DELETE @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Response delete(@PathParam(value = "id") Integer id);

    @GET @Path(value = "/token")
    @Produces(MediaType.TEXT_PLAIN)
    Response getToken();

    @GET @Path(value = "/token/check")
    @Consumes(MediaType.TEXT_PLAIN) @Produces(MediaType.TEXT_PLAIN)
    Response testToken(String token);
}
