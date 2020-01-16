package com.nguyen.filter;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

public class LogResponseFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext clientRequestContext,
                       ClientResponseContext clientResponseContext) throws IOException {

        System.out.println(
                "Client receive response to server: " +
                        clientResponseContext.getStatus());

        String ytb = clientResponseContext.getHeaderString("YTB");
        System.out.println("content header ytb: " + ytb);
    }
}
