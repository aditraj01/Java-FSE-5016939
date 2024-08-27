package com.bookstore.metrics;

import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class CustomBookMetrics {

    private final Counter bookCreationCounter;

    public CustomBookMetrics(MeterRegistry meterRegistry) {
        this.bookCreationCounter = meterRegistry.counter("books_created");
    }

    public void incrementBookCreationCounter() {
        this.bookCreationCounter.increment();
    }
}