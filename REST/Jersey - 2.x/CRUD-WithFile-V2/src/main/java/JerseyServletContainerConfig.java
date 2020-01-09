import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JerseyServletContainerConfig extends ResourceConfig {

    public JerseyServletContainerConfig() {
        packages("com.nguyen, exception");
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                1000));
        register(MultiPartFeature.class);
        register(JacksonFeature.class);
    }
}
