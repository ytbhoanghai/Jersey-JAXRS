package com.nguyen;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyServletContainerConfig extends ResourceConfig {

    public JerseyServletContainerConfig() {
        packages("com.nguyen");
    }
}
