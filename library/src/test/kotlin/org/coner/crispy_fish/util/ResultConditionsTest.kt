package org.coner.crispy_fish.util

import org.assertj.core.api.Assertions
import org.coner.crispy_fish.domain.Result
import org.coner.crispy_fish.util.ResultConditions
import org.junit.Test

class ResultConditionsTest {

    @Test
    fun whenPositionMatches() {
        val result = Result()
        result.position = 1

        val actual = ResultConditions.positionEquals(1).matches(result)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenPositionDoesntMatch() {
        val result = Result()
        result.position = 1

        val actual = ResultConditions.positionEquals(Integer.MAX_VALUE).matches(result)

        Assertions.assertThat(actual).isFalse()
    }
}