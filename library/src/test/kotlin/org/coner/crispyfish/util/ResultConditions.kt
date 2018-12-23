package org.coner.crispyfish.util

import org.assertj.core.api.Condition
import org.assertj.core.condition.AllOf
import org.assertj.core.util.Strings
import org.coner.crispyfish.model.Registration
import org.coner.crispyfish.model.Result
import java.util.function.Predicate

object ResultConditions {

    fun positionEquals(position: Int) = Condition<Result>(
                Predicate{ result -> result.position == position },
                "result.position == %d",
                position
    )

    fun driverNumbersEqual(category: String?, handicap: String, number: String) = Condition<Result>(
            Predicate { result ->
                (result.driver?.category?.abbreviation ?: "").equals(category, ignoreCase = true)
                && result.driver?.handicap?.abbreviation.equals(handicap, ignoreCase = true)
                && result.driver?.number == number
            },
            "result.driver == %s",
            "${category ?: ""}$handicap $number"
    )

    fun driverNameNotNullOrEmpty(): Condition<Result> {
        return Condition(Predicate{ result ->
            !result.driver?.firstName.isNullOrBlank()
            && !result.driver?.lastName.isNullOrBlank()
        }, "result.driver first and last last names are not null or blank")
    }

    fun driverFinished(position: Int, category: String?, handicap: String, number: String): Condition<Result> {
        return AllOf.allOf(
                positionEquals(position),
                driverNumbersEqual(category, handicap, number)
        )
    }
}
