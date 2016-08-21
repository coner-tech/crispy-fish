package org.crispy_fish.filetype.staging;

import org.crispy_fish.domain.EventDay;
import org.crispy_fish.filetype.ecf.EventControlFile;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StagingFileAssistantTest {

    private StagingFileAssistant assistant;

    @Before
    public void setup() {
        assistant = new StagingFileAssistant();
    }

    @Test(expected = NullPointerException.class)
    public void whenBuildStagingFilenameFilterWithNullEventControlFileItShouldThrow() {
        assistant.buildStagingFilenameFilter(null, EventDay.ONE);
    }

    @Test(expected = NullPointerException.class)
    public void whenBuildStagingFilenameFilterNullEventDayItShouldThrow() {
        assistant.buildStagingFilenameFilter(mock(EventControlFile.class), null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenBuildStagingFilenameFilterWithOneDayEventForDayTwoItShouldThrow() {
        EventControlFile eventControlFile = mock(EventControlFile.class);
        when(eventControlFile.isTwoDayEvent()).thenReturn(false);

        assistant.buildStagingFilenameFilter(eventControlFile, EventDay.TWO);

        verify(eventControlFile).isTwoDayEvent();
    }

    @Test
    public void whenBuildStagingFilenameFilterForDayOneItShouldReturnIt() {
        Path eventControlFilePath = Paths.get("foo", "bar", "baz.ecf");
        EventControlFile eventControlFile = mock(EventControlFile.class);
        when(eventControlFile.getPath()).thenReturn(eventControlFilePath);
        when(eventControlFile.isTwoDayEvent()).thenReturn(false);

        StagingFilenameFilter actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.ONE);

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter.class);
        assertThat(actual.getEventFileOriginalStagingBaseName()).isEqualTo("baz");
        assertThat(actual.getOriginalFilePattern()).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_1);
    }

    @Test
    public void whenBuildStagingFilenameFilterForDayTwoItShouldReturnIt() {
        Path eventControlFilePath = Paths.get("foo", "bar", "baz.ecf");
        EventControlFile eventControlFile = mock(EventControlFile.class);
        when(eventControlFile.getPath()).thenReturn(eventControlFilePath);
        when(eventControlFile.isTwoDayEvent()).thenReturn(true);

        StagingFilenameFilter actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.TWO);

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter.class);
        assertThat(actual.getEventFileOriginalStagingBaseName()).isEqualTo("baz");
        assertThat(actual.getOriginalFilePattern()).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_2);
    }
}