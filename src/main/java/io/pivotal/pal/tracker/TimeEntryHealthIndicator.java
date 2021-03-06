package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator{

    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Override
    public Health health() {
        Health status = Health.down().build();

        if (timeEntryRepository.list().size() < 5) {
            status = Health.up().build();
        }
        return status;
    }
}
