package org.crispy_fish.util;

import org.assertj.core.api.Assertions;
import org.crispy_fish.domain.Result;
import org.junit.Test;

public class ResultConditionsTest {

    @Test
    public void whenPositionMatches() {
        Result result = new Result();
        result.position = 1;

        boolean actual = ResultConditions.positionEquals(1).matches(result);

        Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void whenPositionDoesntMatch() {
        Result result = new Result();
        result.position = 1;

        boolean actual = ResultConditions.positionEquals(Integer.MAX_VALUE).matches(result);

        Assertions.assertThat(actual).isFalse();
    }
}