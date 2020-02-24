package com.nguyen.handler_exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        String messages = throwable.getMessage();
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();

        if (throwable instanceof WebApplicationException) {
            status = ((WebApplicationException) throwable).getResponse().getStatus();
        }
        return Response.status(status).entity(messages).type(MediaType.TEXT_PLAIN).build();
    }
}
