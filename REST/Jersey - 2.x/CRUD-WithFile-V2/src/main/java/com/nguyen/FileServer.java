package com.nguyen;

import com.sun.xml.internal.stream.Entity;
import entity.EntityResponse;
import exception.CreateFileFailedException;
import exception.NotAcceptFileExtensionException;
import exception.NotFoundFileException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import utils.FileUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Path(value = "/files")
public class FileServer implements FileServices {

    private static List<String> acceptExtensions;
    static {
        acceptExtensions = Arrays.asList("docx", "doc", "jpg", "rar");
    }

    private String BaseFolder;

    public FileServer(@Context ServletContext servletContext) {
        BaseFolder = servletContext.getRealPath("/files");
    }

    @Override
    public Response createFile(InputStream inputStream, FormDataContentDisposition infoFile) {
        String fileName = infoFile.getFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (!acceptExtensions.contains(fileExtension)) {
            throw new NotAcceptFileExtensionException("Not Accept File Extension");
        }

        File file = FileUtils.storeFile(inputStream, BaseFolder, fileName);
        if (file == null) {
            throw new CreateFileFailedException("Create File Failed");
        }
        EntityResponse response = EntityResponse.builder().
                HttpStatus(Response.Status.OK.getStatusCode()).
                message("Create file success with file name: " + file.getName()).
                build();

        return Response.ok(response).type("application/json").build();
    }

    @Override
    public Response downloadFile(String fileName) {
        if (!FileUtils.getAllFile(BaseFolder).stream().anyMatch(file -> file.getName().equals(fileName))) {
            throw new NotFoundFileException("Not found exception");
        }

        File file = FileUtils.getFile(BaseFolder, fileName);
        String mimeType = new MimetypesFileTypeMap().getContentType(file);
        return Response.status(Response.Status.OK).entity(file).type(mimeType).build();
    }


}
