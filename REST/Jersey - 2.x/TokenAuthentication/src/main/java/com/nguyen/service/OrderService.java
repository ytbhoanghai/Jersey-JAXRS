package com.nguyen.service;

import com.nguyen.model.Order;
import com.nguyen.model.Role;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface OrderService {

    @GET @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findById(@PathParam("id") Integer id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll();

    @RolesAllowed(Role.ROLE_CUSTOMER)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response update(Order order);

    @RolesAllowed({Role.ROLE_ADMIN, Role.ROLE_CUSTOMER})
    @DELETE @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteById(@PathParam("id") Integer id);

    @RolesAllowed(Role.ROLE_ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response insert(Order order);
}
