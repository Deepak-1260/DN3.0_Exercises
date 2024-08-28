package com.example.bookstoreapi.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    public CustomMetrics(MeterRegistry meterRegistry) {
        meterRegistry.gauge("custom_metric", this, CustomMetrics::getCustomMetric);
    }

    public double getCustomMetric() {
        // Example: return a custom metric value
        return Math.random();
    }
}
