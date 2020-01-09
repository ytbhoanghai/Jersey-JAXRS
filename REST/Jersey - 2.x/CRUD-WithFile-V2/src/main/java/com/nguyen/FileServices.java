package com.nguyen;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

public interface FileServices {

    // CRUD
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    Response createFile(@FormDataParam("uploadFile") InputStream stream,
                        @FormDataParam("uploadFile") FormDataContentDisposition infoFile);

    @GET
    @Path(value = "/{filename}")
    @Produces(MediaType.APPLICATION_JSON)
    Response downloadFile(@PathParam("filename") String fileName);
}
