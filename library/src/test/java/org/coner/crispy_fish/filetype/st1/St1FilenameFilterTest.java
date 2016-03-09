package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.filetype.ecf.EcfAssistant;

import java.io.File;
import java.nio.file.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class St1FilenameFilterTest {

    private St1FilenameFilter st1FilenameFilter;

    @Mock
    EcfAssistant ecfAssistant;

    Path eventControlFile;

    @Before
    public void setup() {
        eventControlFile = Paths.get("test", "foo", "bar.ecf");
        when(ecfAssistant.isEcf(eventControlFile)).thenReturn(true);

        st1FilenameFilter = new St1FilenameFilter(ecfAssistant, eventControlFile);
    }

    @Test
    public void whenExtensionIsNormalSt1ItShouldAccept() {
        File dir = null;
        String name = "bar.st1";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isTrue();
    }

    @Test
    public void whenExtensionIsAbsentItShouldReject() {
        File dir = null;
        String name = "bar";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsPresentButWrongItShouldReject() {
        File dir = null;
        String name = "bar.txt";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsReacceptedSt1ItShouldAccept() {
        File dir = null;
        String name = "bar_st1.003";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isTrue();
    }

    @Test
    public void whenExtensionIsSt1LogItShouldReject() {
        File dir = null;
        String name = "bar.st1_log";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenPathContainsMultipleEventsWithReacceptedFileItShouldOnlyAcceptForEvent() {
        File dir = null;
        String name = "bas_st1.000";

        boolean actual = st1FilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }
}
