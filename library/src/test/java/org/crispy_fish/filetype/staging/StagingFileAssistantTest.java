package org.crispy_fish.filetype.staging;

import org.assertj.core.api.Assertions;
import org.coner.crispy_fish.domain.EventDay;
import org.coner.crispy_fish.domain.PenaltyType;
import org.coner.crispy_fish.filetype.ecf.EventControlFile;
import org.coner.crispy_fish.filetype.staging.StagingFileAssistant;
import org.coner.crispy_fish.filetype.staging.StagingFilenameFilter;
import org.coner.crispy_fish.filetype.staging.StagingFilenames;
import org.coner.crispy_fish.filetype.staging.StagingLineException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StagingFileAssistantTest {

    private StagingFileAssistant assistant;

    @Before
    public void setup() {
        assistant = new StagingFileAssistant();
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
        assertThat(actual.getOriginalFilePattern()).isSameAs(StagingFilenames.INSTANCE.getORIGINAL_FILE_DAY_1());
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
        assertThat(actual.getOriginalFilePattern()).isSameAs(StagingFilenames.INSTANCE.getORIGINAL_FILE_DAY_2());
    }

    @Test
    public void whenConvertStagingTimeStringToDurationNormalItShouldWorkLol() throws Exception {
        final Duration expected = Duration.ofSeconds(45).plusMillis(678);

        final Duration actual = assistant.convertStagingTimeStringToDuration("45.678");

        Assertions.assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test(expected = StagingLineException.class)
    public void whenConvertStagingTimeStringToDurationInvalidItShouldThrow() throws Exception {
        assistant.convertStagingTimeStringToDuration("invalid");
    }

    @Test
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeEmptyItShouldReturnClean() throws Exception {
        final String penalty = "";
        final PenaltyType expected = PenaltyType.CLEAN;

        final PenaltyType actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeDidNotFinish() throws Exception {
        final String penalty = "dnf";
        final PenaltyType expected = PenaltyType.DID_NOT_FINISH;

        final PenaltyType actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeDisqualified() throws Exception {
        final String penalty = "dsq";
        final PenaltyType expected = PenaltyType.DISQUALIFIED;

        final PenaltyType actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeRerun() throws Exception {
        final String penalty = "rrn";
        final PenaltyType expected = PenaltyType.RERUN;

        final PenaltyType actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeCone() throws Exception {
        final String penalty = "2";
        final PenaltyType expected = PenaltyType.CONE;

        final PenaltyType actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);

        assertThat(actual).isSameAs(expected);
    }

    @Test(expected = StagingLineException.class)
    public void whenConvertStagingRunPenaltyStringToPenaltyTypeUnrecognized() throws Exception {
        final String penalty = "*unrecognized*";

        assistant.convertStagingRunPenaltyStringToPenaltyType(penalty);
    }

    @Test
    public void whenConvertStagingRunPenaltyToConeCountNormal() throws Exception {
        final String penalty = "8";
        final int expected = 8;

        int actual = assistant.convertStagingRunPenaltyStringToConeCount(penalty);

        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = StagingLineException.class)
    public void whenConvertStagingRunPenaltyToConeCountInvalid() throws Exception {
        final String penalty = "-1";

        assistant.convertStagingRunPenaltyStringToConeCount(penalty);
    }
}
