package org.coner.crispy_fish.domain.run;

import org.coner.crispy_fish.domain.driver.Driver;

import java.time.*;

public class Run {

    private final int index;
    private final int number;
    private final Driver driver;
    private final Duration timeRaw;
    private final Duration timePax;
    private final Instant timestamp;

    public Run(int index, int number, Driver driver, Duration timeRaw, Duration timePax, Instant timestamp) {
        this.index = index;
        this.number = number;
        this.driver = driver;
        this.timeRaw = timeRaw;
        this.timePax = timePax;
        this.timestamp = timestamp;
    }

    private Run(Builder builder) {
        index = builder.index;
        number = builder.number;
        driver = builder.driver;
        timeRaw = builder.timeRaw;
        timePax = builder.timePax;
        timestamp = builder.timestamp;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getIndex() {
        return index;
    }

    public int getNumber() {
        return number;
    }

    public Driver getDriver() {
        return driver;
    }

    public Duration getTimeRaw() {
        return timeRaw;
    }

    public Duration getTimePax() {
        return timePax;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public static final class Builder {
        private int index;
        private int number;
        private Driver driver;
        private Duration timeRaw;
        private Duration timePax;
        private Instant timestamp;

        private Builder() {
        }

        public Builder index(int val) {
            index = val;
            return this;
        }

        public Builder number(int val) {
            number = val;
            return this;
        }

        public Builder driver(Driver val) {
            driver = val;
            return this;
        }

        public Builder timeRaw(Duration val) {
            timeRaw = val;
            return this;
        }

        public Builder timePax(Duration val) {
            timePax = val;
            return this;
        }

        public Builder timestamp(Instant val) {
            timestamp = val;
            return this;
        }

        public Run build() {
            return new Run(this);
        }
    }
}
