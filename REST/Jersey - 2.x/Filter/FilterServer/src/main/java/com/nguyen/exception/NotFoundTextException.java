package com.nguyen.exception;

import com.nguyen.entity.ResponseEntity;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Date;

public class NotFoundTextException extends WebApplicationException {

    public NotFoundTextException(Integer id) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(new ResponseEntity(
                        Response.Status.NOT_FOUND,
                        new Date(),
                        "Server not found text with id: " + id))
                .build());
    }
}
