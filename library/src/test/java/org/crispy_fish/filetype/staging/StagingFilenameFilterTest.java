package org.crispy_fish.filetype.staging;

import java.util.regex.Pattern;

import org.coner.crispy_fish.filetype.staging.StagingFilenameFilter;
import org.coner.crispy_fish.filetype.staging.StagingFilenames;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StagingFilenameFilterTest {

    private StagingFilenameFilter stagingFilenameFilter;

    private static final String EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME = "bar";
    private static final Pattern STAGING_ORIGINAL_FILE_PATTERN = StagingFilenames.INSTANCE.getORIGINAL_FILE_DAY_1();

    @Rule
    public final TemporaryFolder dir = new TemporaryFolder();

    @Before
    public void setup() {
        stagingFilenameFilter = new StagingFilenameFilter(
                EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME,
                STAGING_ORIGINAL_FILE_PATTERN
        );
    }

    @Test
    public void whenExtensionIsNormalStagingItShouldAccept() {
        String name = "bar.st1";

        boolean actual = stagingFilenameFilter.accept(dir.getRoot(), name);

        assertThat(actual).isTrue();
    }

    @Test
    public void whenExtensionIsAbsentItShouldReject() {
        String name = "bar";

        boolean actual = stagingFilenameFilter.accept(dir.getRoot(), name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsPresentButWrongItShouldReject() {
        String name = "bar.txt";

        boolean actual = stagingFilenameFilter.accept(dir.getRoot(), name);

        assertThat(actual).isFalse();
    }

    @Test
    public void whenExtensionIsStagingLogItShouldReject() {
        String name = "bar.st1_log";

        boolean actual = stagingFilenameFilter.accept(dir.getRoot(), name);

        assertThat(actual).isFalse();
    }
}
