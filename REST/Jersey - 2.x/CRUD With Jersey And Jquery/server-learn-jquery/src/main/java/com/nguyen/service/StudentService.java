package com.nguyen.service;

import com.nguyen.model.Role;
import com.nguyen.model.Student;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface StudentService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response doGetAllStudent();

    @GET @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response doGetStudentById(@PathParam("id") String id);

    @POST
    @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
    Response doPostStudent(Student student);

    @RolesAllowed({Role.ADMIN})
    @DELETE @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response doDeleteStudentById(@PathParam("id") String id);

    @RolesAllowed({Role.ADMIN})
    @PUT @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response doPutStudent(@PathParam("id") String id, Student student);
}
