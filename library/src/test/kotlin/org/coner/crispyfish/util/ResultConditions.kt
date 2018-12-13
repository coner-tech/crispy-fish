package org.coner.crispyfish.util

import org.assertj.core.api.Condition
import org.assertj.core.condition.AllOf
import org.assertj.core.util.Strings
import org.coner.crispyfish.domain.Result
import org.coner.crispyfish.domain.Numbers
import java.util.function.Predicate

object ResultConditions {

    fun positionEquals(position: Int) = Condition<Result>(
                Predicate{ result -> result.position == position },
                "result.position == %d",
                position
    )

    fun driverNumbersEqual(numbers: Numbers) = Condition<Result>(
            Predicate{ result -> result.driver!!.numbers == numbers },
            "result.driver == %s",
            numbers.toString()
    )

    fun driverNumbersEqual(classing: String, number: String): Condition<Result> {
        return driverNumbersEqual(Numbers(classing, number))
    }

    fun driverNameNotNullOrEmpty(): Condition<Result> {
        return Condition(Predicate{ result -> !Strings.isNullOrEmpty(result.driver!!.name) }, "result.driver is not null or empty")
    }

    fun driverFinished(position: Int, classing: String, number: String): Condition<Result> {
        return AllOf.allOf(
                positionEquals(position),
                driverNumbersEqual(classing, number)
        )
    }
}
