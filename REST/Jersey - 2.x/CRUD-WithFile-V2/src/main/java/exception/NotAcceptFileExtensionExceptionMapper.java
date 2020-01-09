package exception;

import entity.EntityResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptFileExtensionExceptionMapper implements ExceptionMapper<NotAcceptFileExtensionException> {
    @Override
    public Response toResponse(NotAcceptFileExtensionException e) {
        EntityResponse response = new EntityResponse(
                Response.Status.NOT_ACCEPTABLE.getStatusCode(),
                e.getMessage());

        return Response.status(response.getHttpStatus()).
                entity(response).
                type(MediaType.APPLICATION_JSON).build();
    }
}
