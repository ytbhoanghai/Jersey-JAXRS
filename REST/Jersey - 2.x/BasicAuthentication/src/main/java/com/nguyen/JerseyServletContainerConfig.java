package com.nguyen;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class JerseyServletContainerConfig extends ResourceConfig {
    public JerseyServletContainerConfig() {
        packages("com.nguyen");
        register(JacksonFeature.class);
        register(RolesAllowedDynamicFeature.class);
    }
}
