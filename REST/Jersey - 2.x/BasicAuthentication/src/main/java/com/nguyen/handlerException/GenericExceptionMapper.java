package com.nguyen.handlerException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        String message = throwable.getMessage();
        Response.StatusType status = Response.Status.INTERNAL_SERVER_ERROR;
        if (throwable instanceof WebApplicationException) {
            status = ((WebApplicationException) throwable).getResponse().getStatusInfo();
        }
        return Response.status(status).entity(message).type(MediaType.TEXT_PLAIN).build();
    }
}
