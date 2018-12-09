package org.crispy_fish.util;

import org.assertj.core.api.Condition;
import org.assertj.core.condition.AllOf;
import org.assertj.core.util.Strings;
import org.coner.crispy_fish.domain.Result;
import org.crispy_fish.domain.payload.Driver;

public class ResultConditions {

    public static Condition<Result> positionEquals(final int position) {
        return new Condition<>(result -> result.getPosition() == position, "result.position == %d", position);
    }

    public static Condition<Result> driverEquals(final Driver driver) {
        return new Condition<>(result -> result.getDriver().equals(driver), "result.driver == %s", driver.toString());
    }

    public static Condition<Result> driverEquals(final String classing, final String number) {
        Driver driver = new Driver();
        driver.classing = classing;
        driver.number = number;
        return driverEquals(driver);
    }

    public static Condition<Result> driverNameNotNullOrEmpty() {
        return new Condition<>(result -> !Strings.isNullOrEmpty(result.getDriver().name), "result.driver is not null or empty");
    }

    public static Condition<Result> driverFinished(final int position, final String classing, final String number) {
        return AllOf.allOf(
                positionEquals(position),
                driverEquals(classing, number)
        );
    }
}
