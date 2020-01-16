package com.nguyen.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetTokenRequestFilter implements ClientRequestFilter {

    private static String token;
    static {
        token = getProperties().getProperty("token");
    }

    public static Properties getProperties() {
        Properties _properties = new Properties();

        try (InputStream inputStream = new FileInputStream("src/main/resources/app.properties")) {
            _properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _properties;
    }

    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        String token = clientRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.isEmpty()) {
            clientRequestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, SetTokenRequestFilter.token);
        }
    }
}
