package org.coner.crispy_fish.filetype.staging;

import java.io.File;
import java.util.regex.Pattern;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StagingFilenameFilterTest {

    private StagingFilenameFilter stagingFilenameFilter;

    private static final String EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME = "bar";
    private static final Pattern STAGING_ORIGINAL_FILE_PATTERN = StagingFilenames.ORIGINAL_FILE_DAY_1;

    @Before
    public void setup() {
        stagingFilenameFilter = new StagingFilenameFilter(
                EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME,
                STAGING_ORIGINAL_FILE_PATTERN
        );
    }

    @Test
    public void whenExtensionIsNormalStagingItShouldAccept() {
        File dir = null;
        String name = "bar.st1";

        boolean actual = stagingFilenameFilter.accept(dir, name);

        assertThat(actual).isTrue();
    }

    @Test
    public void whenExtensionIsAbsentItShouldReject() {
        File dir = null;
        String name = "bar";

        boolean actual = stagingFilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsPresentButWrongItShouldReject() {
        File dir = null;
        String name = "bar.txt";

        boolean actual = stagingFilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsStagingLogItShouldReject() {
        File dir = null;
        String name = "bar.st1_log";

        boolean actual = stagingFilenameFilter.accept(dir, name);

        assertThat(actual).isFalse();
    }
}
