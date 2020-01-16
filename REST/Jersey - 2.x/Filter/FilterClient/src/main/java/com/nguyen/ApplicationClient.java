package com.nguyen;

import com.nguyen.entity.ResponseEntity;
import com.nguyen.entity.Text;
import com.nguyen.filter.LogResponseFilter;
import com.nguyen.filter.SetTokenRequestFilter;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ApplicationClient {


    private static String URL = "http://localhost:8080/Filter/rest/text";

    public static Client getClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register(SetTokenRequestFilter.class);
        clientConfig.register(LogResponseFilter.class);

        return ClientBuilder.newClient(clientConfig);
    }

    public static void main(String[] args) {
        System.out.println(selectAll());

    }

    private static List<Text> selectAll() {
        Client client = ApplicationClient.getClient();
        WebTarget webTarget = client.target(URL);

        GenericType<List<Text>> listGenericType = new GenericType<List<Text>>(){};

        Response response = webTarget.request().
                accept(MediaType.APPLICATION_JSON_TYPE).
                get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }
        return response.readEntity(listGenericType);
    }

    private static Text selectById(Integer id) {
        Client client = getClient();
        WebTarget target = client.target(URL).path("" + id);

        Response response = target.request().
                accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }

        return response.readEntity(Text.class);
    }

    private static String update(Text text) {
        Client client = getClient();
        WebTarget target = client.target(URL);

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).
                accept(MediaType.TEXT_PLAIN).
                put(Entity.json(text));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            ResponseEntity entity = response.readEntity(ResponseEntity.class);
            return entity.getContent();
        }

        return response.readEntity(String.class);
    }

    private static ResponseEntity insert(Text text) {
        Client client = getClient();
        WebTarget target = client.target(URL);

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).
                accept(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.json(text));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }
        return response.readEntity(ResponseEntity.class);
    }

    private static String delete(Integer id) {
        Client client = getClient();
        WebTarget target = client.target(URL).path("" + id);

        Response response = target.request().delete();
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return response.readEntity(ResponseEntity.class).getContent();
        }

        return response.readEntity(String.class);
    }

    private static String getToken() {
        Client client = getClient();
        WebTarget target = client.target(URL).path("token");

        Response response = target.request().accept(MediaType.TEXT_PLAIN).get();
        String token = response.readEntity(String.class);

        return token;
    }
}
