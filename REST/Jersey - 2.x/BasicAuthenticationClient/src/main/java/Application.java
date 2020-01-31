import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Application {

    private static final String URL = "http://localhost:8080/BasicAuthentication/rest/api";

    private static Client getClient() {
        Client client = ClientBuilder.newClient();

        client.register(HttpAuthenticationFeature.
                basic("customer", "123"));
        client.register(JacksonFeature.class);

        return client;
    }

    public static void main(String[] args) {

        Client client = getClient();
        WebTarget target = client.target(URL).path("order");
//        Response response = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get();

//        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).
//                accept(MediaType.APPLICATION_JSON_TYPE).
//                put(Entity.json(new Order(1,"nguyen hai")));

//        Response response = target.request().delete();

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).
                accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(new Order(1, "abc")));
        System.out.println(response.readEntity(String.class));
    }
}
