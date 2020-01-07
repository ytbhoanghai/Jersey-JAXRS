package com.nguyen.ws;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface UserService {

    // CURD

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    User insert(User user);

    @PUT
    @Path(value = "/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    User update(@PathParam(value = "id") Integer id, User user);

    @GET
    @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    User select(@PathParam(value = "id") Integer id);

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    User[] selectAll();

    @DELETE
    @Path(value = "/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    User delete(@PathParam(value = "id") Integer id);
}
