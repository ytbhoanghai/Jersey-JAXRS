package com.nguyen.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException {

        MultivaluedMap<String, Object> header = containerResponseContext.getHeaders();
        header.add("Access-Control-Allow-Origin", "*");
        header.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        header.add("Access-Control-Allow-Headers", "X-Request-With, Content-Type, Authorization");

    }
}
