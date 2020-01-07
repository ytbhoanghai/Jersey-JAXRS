package com.nguyen.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface UserService {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    User[] getAll();

    @GET
    @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    User getById(@PathParam(value = "id") Integer id);

    @PUT
    @Path(value = "/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    User update(@PathParam(value = "id") Integer id, User user);

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    User create(User user);

    @DELETE
    @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    User delete(@PathParam(value = "id") Integer id);
}
