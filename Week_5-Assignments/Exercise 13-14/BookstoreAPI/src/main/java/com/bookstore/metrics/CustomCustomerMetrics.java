package com.bookstore.metrics;

import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class CustomCustomerMetrics {

    private final Counter customerCreationCounter;

    public CustomCustomerMetrics(MeterRegistry meterRegistry) {
        this.customerCreationCounter = meterRegistry.counter("customer_data_created");
    }

    public void incrementBookCreationCounter() {
        this.customerCreationCounter.increment();
    }
}
