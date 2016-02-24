package org.coner.crispy_fish.domain.car;

public class Car {

    private final String color;
    private final String description;

    public Car(String color, String description) {
        this.color = color;
        this.description = description;
    }

    private Car(Builder builder) {
        color = builder.color;
        description = builder.description;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public static final class Builder {
        private String color;
        private String description;

        private Builder() {
        }

        public Builder color(String val) {
            color = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
