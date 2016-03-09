package org.coner.crispy_fish.filetype.ecf;

import java.nio.file.*;
import org.assertj.core.api.Assertions;
import org.junit.*;

public class EcfAssistantTest {

    private EcfAssistant ecfAssistant;

    @Before
    public void setup() {
        ecfAssistant = new EcfAssistant();
    }

    @Test
    public void whenIsEcfWithValidPathItShouldReturnTrue() {
        Path path = Paths.get("foo.ecf");

        boolean actual = ecfAssistant.isEcf(path);

        Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void whenIsEcfWithInvalidPathItShouldReturnFalse() {
        Path path = Paths.get("foo.wrong");

        boolean actual = ecfAssistant.isEcf(path);

        Assertions.assertThat(actual).isFalse();
    }

}
