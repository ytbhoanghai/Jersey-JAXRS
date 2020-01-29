package com.nguyen.service;

import com.nguyen.model.Order;
import com.nguyen.model.Role;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public interface OrderService {

    @GET @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response get(@PathParam("id") Integer id);

    @POST
    @RolesAllowed(Role.ROLE_CUSTOMER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response insert(Order order, @Context SecurityContext context);

    @PUT
    @RolesAllowed({Role.ROLE_CUSTOMER, Role.ROLE_ADMIN})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response update(Order order);

    @DELETE @Path("/{id}")
    @RolesAllowed(Role.ROLE_ADMIN)
    @Produces(MediaType.APPLICATION_JSON)
    Response delete(@PathParam("id") Integer id);
}
