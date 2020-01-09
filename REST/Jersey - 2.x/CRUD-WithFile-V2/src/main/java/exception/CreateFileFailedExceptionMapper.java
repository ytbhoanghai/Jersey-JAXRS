package exception;

import entity.EntityResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CreateFileFailedExceptionMapper implements ExceptionMapper<CreateFileFailedException> {
    @Override
    public Response toResponse(CreateFileFailedException e) {
        EntityResponse response = EntityResponse.builder().
                HttpStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).
                message(e.getMessage()).build();

        return Response.status(response.getHttpStatus()).
                entity(response).
                type(MediaType.APPLICATION_JSON).build();
    }
}
