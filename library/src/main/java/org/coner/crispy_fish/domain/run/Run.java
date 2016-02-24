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
}
