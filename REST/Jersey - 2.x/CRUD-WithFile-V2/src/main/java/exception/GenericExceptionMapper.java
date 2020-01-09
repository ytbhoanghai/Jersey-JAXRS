package exception;

import entity.EntityResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        EntityResponse response = EntityResponse.builder().
                HttpStatus(getResponseStatus(throwable).getStatusCode()).
                message(throwable.getMessage()).build();

        return Response.status(response.getHttpStatus()).
                entity(response).
                type(MediaType.APPLICATION_JSON).build();
    }

    private Response.StatusType getResponseStatus(Throwable throwable) {
        if (throwable instanceof WebApplicationException) {
            return ((WebApplicationException) throwable).getResponse().getStatusInfo();
        }
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}
