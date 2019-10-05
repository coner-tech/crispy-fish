package org.coner.crispyfish.util

import org.assertj.core.api.Assertions
import org.coner.crispyfish.model.Registration
import org.coner.crispyfish.model.Result
import org.junit.Test
import org.mockito.Mockito

class ResultConditionsTest {

    @Test
    fun whenPositionMatches() {
        val result = Result(
                driver = Mockito.mock(Registration::class.java),
                position = 1,
                time = ""
        )

        val actual = ResultConditions.positionEquals(1).matches(result)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenPositionDoesntMatch() {
        val result = Result(
                driver = Mockito.mock(Registration::class.java),
                position = 1,
                time = ""
        )

        val actual = ResultConditions.positionEquals(Integer.MAX_VALUE).matches(result)

        Assertions.assertThat(actual).isFalse()
    }
}