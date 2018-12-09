package org.crispy_fish.filetype.staging;

import org.coner.crispy_fish.domain.PenaltyType;
import org.crispy_fish.domain.payload.Driver;
import org.crispy_fish.domain.payload.Run;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StagingLineDomainReaderTest {

    private StagingLineDomainReader<String> stagingLineDomainReader;

    @Mock
    StagingFileAssistant stagingFileAssistant;
    @Mock
    StagingLineReader<String> stagingLineReader;

    private final String STAGING_LINE_MOCK = "mock staging line (content not representative)";

    @Before
    public void setup() {
        stagingLineDomainReader = new StagingLineDomainReader<>(stagingFileAssistant, stagingLineReader);
    }

    @Test
    public void whenReadDriver() {
        final String driverName = "driver name";
        final String driverCar = "driver car";
        final String driverClassing = "driver classing";
        final String driverNumber = "driver number";
        when(stagingLineReader.getRegisteredDriverName(STAGING_LINE_MOCK)).thenReturn(driverName);
        when(stagingLineReader.getRegisteredDriverCar(STAGING_LINE_MOCK)).thenReturn(driverCar);
        when(stagingLineReader.getRegisteredDriverClass(STAGING_LINE_MOCK)).thenReturn(driverClassing);
        when(stagingLineReader.getRegisteredDriverNumber(STAGING_LINE_MOCK)).thenReturn(driverNumber);

        final Driver actual = stagingLineDomainReader.readDriver(STAGING_LINE_MOCK);

        assertThat(actual).isNotNull();
        assertThat(actual.name).isSameAs(driverName);
        assertThat(actual.car).isSameAs(driverCar);
        assertThat(actual.classing).isSameAs(driverClassing);
        assertThat(actual.number).isSameAs(driverNumber);
    }

    @Test
    public void whenReadRunNormalClean() throws Exception {
        final String raw = "raw";
        final String pax = "pax";
        final String penalty = "penalty";
        final Duration rawTime = Duration.ofSeconds(56).plusMillis(789);
        final Duration paxTime = Duration.ofSeconds(45).plusMillis(678);
        final PenaltyType penaltyType = PenaltyType.CLEAN;
        when(stagingLineReader.getRunRawTime(STAGING_LINE_MOCK)).thenReturn(raw);
        when(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax);
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingTimeStringToDuration(raw)).thenReturn(rawTime);
        when(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenReturn(paxTime);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNotNull();
        assertThat(actual.rawTime).isSameAs(rawTime);
        assertThat(actual.paxTime).isSameAs(paxTime);
        assertThat(actual.penaltyType).isSameAs(penaltyType);
        assertThat(actual.cones).isEqualTo(0);
    }

    @Test
    public void whenReadRunNormalCone() throws Exception {
        final String penalty = "2";
        final int cones = 2;
        final PenaltyType penaltyType = PenaltyType.CONE;
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty)).thenReturn(cones);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNotNull();
        assertThat(actual.penaltyType).isSameAs(penaltyType);
        assertThat(actual.cones).isEqualTo(cones);
    }

    @Test
    public void whenReadRunNormalOtherPenaltyUnknownCones() throws Exception {
        final String penalty = "DNF";
        final PenaltyType penaltyType = PenaltyType.DID_NOT_FINISH;
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNotNull();
        assertThat(actual.penaltyType).isSameAs(penaltyType);
        assertThat(actual.cones).isEqualTo(Run.CONES_UNKNOWN);
    }

    @Test
    public void whenReadRunConvertRawTimeThrowsItShouldReturnNull() throws Exception {
        final String raw = "raw";
        when(stagingLineReader.getRunRawTime(STAGING_LINE_MOCK)).thenReturn(raw);
        when(stagingFileAssistant.convertStagingTimeStringToDuration(raw)).thenThrow(StagingLineException.class);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNull();
    }

    @Test
    public void whenReadRunConvertPaxTimeThrowsOnCleanRunItShouldReturnNull() throws Exception {
        final String pax = "pax";
        final String penalty = "penalty";
        when(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax);
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(PenaltyType.CLEAN);
        when(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenThrow(StagingLineException.class);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNull();
    }

    @Test
    public void whenReadRunConvertPaxTimeThrowsOnDnfRunItShouldReturnRun() throws Exception {
        final String pax = "pax";
        final String penalty = "penalty";
        when(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax);
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(PenaltyType.DID_NOT_FINISH);
        when(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenThrow(StagingLineException.class);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNotNull();
    }

    @Test
    public void whenReadRunConvertPenaltyToPenaltyTypeThrowsItShouldReturnNull() throws Exception {
        final String penalty = "eleventy";
        when(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty);
        when(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenThrow(StagingLineException.class);

        final Run actual = stagingLineDomainReader.readRun(STAGING_LINE_MOCK);

        assertThat(actual).isNull();
    }
}