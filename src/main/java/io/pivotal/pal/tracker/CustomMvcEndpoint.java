package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * Adapter to expose {@link CustomEndpoint} as an {@link MvcEndpoint}.
 *
 * @author Ted
 * @author Randy
 */
@ConfigurationProperties(prefix = "endpoints.custom")
public class CustomMvcEndpoint extends EndpointMvcAdapter {

    private final CustomEndpoint delegate;

    public CustomMvcEndpoint(CustomEndpoint delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @CustomActuatorGetMapping("/{name:.*}")
    @ResponseBody
    @HypermediaDisabled
    public Object value(@PathVariable String name) {
        if (!this.delegate.isEnabled()) {
            // Shouldn't happen - MVC endpoint shouldn't be registered when delegate's
            // disabled
            return getDisabledResponse();
        }
        return "Hello";
    }





}
