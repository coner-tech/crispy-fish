package org.crispy_fish.filetype.ecf;

import org.assertj.core.api.Assertions;
import org.coner.crispy_fish.filetype.ecf.EventControlFileAssistant;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void whenIsEcfWithRealPathItShouldReturnTrue() {
        Path path = Paths.get("/home/me/Projects/Club/2016/2016-03-05 points autox 1 danville.ecf");

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
