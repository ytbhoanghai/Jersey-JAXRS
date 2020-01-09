import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientRestfulJersey2_2 {

    private static final String URL_API = "http://localhost:8080/RestfulJersey/rest/users";

    private static Client createJerseyRestClient() {
        ClientConfig clientConfig = new ClientConfig();

        clientConfig.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.ALL,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                10000));
        return ClientBuilder.newClient(clientConfig);
    }

    public static void main(String[] args) {
//        // Select User
//        System.out.println("SELECT USER");
//        Integer idUser = 2;
//        System.out.println(ClientRestfulJersey2_2.select(idUser));
//
//        // Select All
//        System.out.println("SELECT ALL");
//        List<User> users = ClientRestfulJersey2_2.selectAll();
//        users.forEach(user -> System.out.println(user));
//
//        // Insert User
//        System.out.println("INSERT USER");
//        User user = new User(null, "join");
//        System.out.println(ClientRestfulJersey2_2.insert(user));

//        // Update User
//        System.out.println("UPDATE USER");
//        System.out.println(ClientRestfulJersey2_2.update(5, new User(null, "master")));

//        System.out.println(ClientRestfulJersey2_2.delete(6));
//        System.out.println(ClientRestfulJersey2_2.delete(7));
//        System.out.println(ClientRestfulJersey2_2.delete(8));
//        System.out.println(ClientRestfulJersey2_2.delete(9));
//        System.out.println(ClientRestfulJersey2_2.delete(10));
//        System.out.println(ClientRestfulJersey2_2.delete(11));
//        System.out.println(ClientRestfulJersey2_2.delete(12));
//        System.out.println(ClientRestfulJersey2_2.delete(13));
//        System.out.println(ClientRestfulJersey2_2.delete(14));
    }

    // CRUD

    private static User insert(User user) {
        Client client = ClientRestfulJersey2_2.createJerseyRestClient();
        WebTarget target = client.target(URL_API);
        return target.request(MediaType.APPLICATION_JSON).post(Entity.json(user), User.class);
    }

    private static User select(Integer id) {
        Client client = ClientRestfulJersey2_2.createJerseyRestClient();
        WebTarget target = client.target(URL_API).path("" + id);
        return target.request().get(User.class);
    }

    private static List<User> selectAll() {
        Client client = ClientRestfulJersey2_2.createJerseyRestClient();
        WebTarget target = client.target(URL_API);

        GenericType<List<User>> genericType = new GenericType<List<User>>(){};
        return target.request().get(genericType);
    }

    private static User update(Integer id, User user) {
        Client client = ClientRestfulJersey2_2.createJerseyRestClient();
        WebTarget target = client.target(URL_API).path("" + id);

        return target.request(MediaType.APPLICATION_JSON).put(Entity.json(user), User.class);
    }

    private static User delete(Integer id) {
        Client client = ClientRestfulJersey2_2.createJerseyRestClient();
        WebTarget target = client.target(URL_API).path("" + id);

        return target.request().delete(User.class);
    }

}
