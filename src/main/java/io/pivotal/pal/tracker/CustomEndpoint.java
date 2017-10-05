package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import java.util.*;

public class CustomEndpoint extends AbstractEndpoint<Map<String, Object>> {





    /**
     * Create a new {@link MetricsEndpoint} instance.
     * {@link AnnotationAwareOrderComparator}.
     */
    public CustomEndpoint() {
        super("custom");



    }



    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("Hello","Randy");
        return result;
    }

}

