package org.crispy_fish.domain.payload;

import java.util.Objects;

public class Driver {

    public String classing;
    public String number;
    public String name;
    public String car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(classing, driver.classing) &&
                Objects.equals(number, driver.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classing, number);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "classing='" + classing + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", car='" + car + '\'' +
                '}';
    }
}
