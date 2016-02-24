package org.coner.crispy_fish.domain.car;

public class Car {

    private final String color;
    private final String description;

    public Car(String color, String description) {
        this.color = color;
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }
}
