package org.crispy_fish.util;

import org.assertj.core.api.Condition;
import org.assertj.core.condition.AllOf;
import org.assertj.core.util.Strings;
import org.coner.crispy_fish.domain.Result;
import org.coner.crispy_fish.domain.Numbers;

public class ResultConditions {

    public static Condition<Result> positionEquals(final int position) {
        return new Condition<>(result -> result.getPosition() == position, "result.position == %d", position);
    }

    public static Condition<Result> driverNumbersEqual(final Numbers numbers) {
        return new Condition<>(result -> result.getDriver().getNumbers().equals(numbers), "result.driver == %s", numbers.toString());
    }

    public static Condition<Result> driverNumbersEqual(final String classing, final String number) {
        Numbers numbers = new Numbers(classing, number);
        return driverNumbersEqual(numbers);
    }

    public static Condition<Result> driverNameNotNullOrEmpty() {
        return new Condition<>(result -> !Strings.isNullOrEmpty(result.getDriver().getName()), "result.driver is not null or empty");
    }

    public static Condition<Result> driverFinished(final int position, final String classing, final String number) {
        return AllOf.allOf(
                positionEquals(position),
                driverNumbersEqual(classing, number)
        );
    }
}
