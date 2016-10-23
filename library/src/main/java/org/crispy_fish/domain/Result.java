package org.crispy_fish.domain;

import org.crispy_fish.domain.payload.Driver;
import org.crispy_fish.domain.payload.Run;

public class Result {

    public int position;
    public Driver driver;
    public Run run;

    @Override
    public String toString() {
        return "Result{" +
                "position=" + position +
                ", driver=" + driver +
                ", run=" + run +
                '}';
    }
}
