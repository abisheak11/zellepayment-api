package com.zellepayment.acuator;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReportingHealth implements HealthIndicator {
    @Override
    public @Nullable Health health() {
        boolean isHealthy = checkReportingServicesHealth();
        if (isHealthy){
            return Health.up().withDetail("Reporting Services","Available").build();
        }else {
            return Health.up().withDetail("Reporting Services","Unavailable").build();
        }
    }
    private boolean checkReportingServicesHealth(){
        return true;
    }
}
