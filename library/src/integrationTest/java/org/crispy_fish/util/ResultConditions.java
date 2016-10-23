package org.crispy_fish.util;

import org.assertj.core.api.Condition;
import org.assertj.core.condition.AllOf;
import org.crispy_fish.domain.Result;
import org.crispy_fish.domain.payload.Driver;

public class ResultConditions {

    public static Condition<Result> positionEquals(final int position) {
        return new Condition<>(result -> result.position == position, "result.position == %d", position);
    }

    public static Condition<Result> driverEquals(final Driver driver) {
        return new Condition<>(result -> result.driver.equals(driver), "result.driver == %s", driver.toString());
    }

    public static Condition<Result> driverEquals(final String classing, final String number) {
        Driver driver = new Driver();
        driver.classing = classing;
        driver.number = number;
        return driverEquals(driver);
    }

    public static Condition<Result> driverFinished(final int position, final String classing, final String number) {
        return AllOf.allOf(
                positionEquals(position),
                driverEquals(classing, number)
        );
    }
}
