package org.coner.crispy_fish.filetype.ecf;

import java.nio.file.*;
import org.assertj.core.api.Assertions;
import org.junit.*;

public class EventControlFileAssistantTest {

    private EventControlFileAssistant eventControlFileAssistant;

    @Before
    public void setup() {
        eventControlFileAssistant = new EventControlFileAssistant();
    }

    @Test
    public void whenIsEcfWithValidPathItShouldReturnTrue() {
        Path path = Paths.get("foo.ecf");

        boolean actual = eventControlFileAssistant.isEventControlFilePath(path);

        Assertions.assertThat(actual).isTrue();
    }

    @Test
    public void whenIsEcfWithInvalidPathItShouldReturnFalse() {
        Path path = Paths.get("foo.wrong");

        boolean actual = eventControlFileAssistant.isEventControlFilePath(path);

        Assertions.assertThat(actual).isFalse();
    }

}
