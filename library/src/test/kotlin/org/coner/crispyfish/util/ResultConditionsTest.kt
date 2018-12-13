package org.coner.crispyfish.util

import org.assertj.core.api.Assertions
import org.coner.crispyfish.domain.Result
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