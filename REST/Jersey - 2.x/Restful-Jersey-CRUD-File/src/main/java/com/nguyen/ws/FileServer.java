package com.nguyen.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Path(value = "/files")
public class FileServer {

    private List<FileUploadResponse> files;
    private String BASE_FOLDER;

    public FileServer(@Context ServletContext context) {
        System.out.println("Start Servlet");
        BASE_FOLDER = context.getRealPath("/files");

        files = new ArrayList<>();
        List<File> files = FileUtils.getAllFile(BASE_FOLDER + "/download");
        files.forEach(file -> {
            FileUploadResponse entity = FileUploadResponse.builder().
                    fileName(file.getName()).
                    fileSizeInByte(file.length()).build();
            this.files.add(entity);
        });
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response selectAll() throws JsonProcessingException {
        if (files.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).entity("File Not Found").build();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(files);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path(value = "/download/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response download(@PathParam(value = "fileName") String fileName) {
        File file = FileUtils.getFile(fileName, BASE_FOLDER + "/download");
        if (file == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("File Not Found").build();
        }

        String mimeType = new MimetypesFileTypeMap().getContentType(file);
        Response.ResponseBuilder responseBuilder = Response.ok(file, mimeType);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        return responseBuilder.build();
    }

    @POST
    @Path(value = "/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(
            @FormDataParam("uploadFile") InputStream inputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition contentDisposition) throws FileNotFoundException {

        String fileName = contentDisposition.getFileName();
        File file = FileUtils.storeFile(inputStream, BASE_FOLDER + "/upload", fileName);
        if(file == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("File Not Found").build();
        }

        FileUploadResponse response = FileUploadResponse.builder().
                fileName(file.getName()).
                fileSizeInByte(file.length()).build();

        FileUtils.storeFile(new FileInputStream(file), BASE_FOLDER + "/download", fileName);
        if (!files.stream().anyMatch(entity -> entity.getFileName().equals(fileName))) {
            files.add(response);
        }

        return Response.ok("Success Store File").entity(response).build();
    }

    @DELETE
    @Path(value = "/delete/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam(value = "fileName") String fileName) {
        if (FileUtils.delete(fileName, BASE_FOLDER + "/download")) {
            return Response.ok().entity("Delete Success").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("File Not Found").build();
    }

    @PUT
    @Path(value = "/update/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormDataParam("updateFile") InputStream inputStream,
                           @FormDataParam("updateFile") FormDataContentDisposition contentDisposition,
                           @PathParam(value = "fileName") String fileName) throws FileNotFoundException {

        File fileUpdateUpload = FileUtils.updateFile(inputStream, BASE_FOLDER + "/upload", fileName);
        if (fileUpdateUpload != null) {
            if (FileUtils.getFile(fileName, BASE_FOLDER + "/download") != null) {
                FileUtils.updateFile(new FileInputStream(fileUpdateUpload), BASE_FOLDER + "/download", fileName);
            }

            String mimeType = new MimetypesFileTypeMap().getContentType(fileUpdateUpload);
            Response.ResponseBuilder responseBuilder = Response.ok(fileUpdateUpload, mimeType);
            responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            return responseBuilder.build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("File Not Found").build();
    }

}
