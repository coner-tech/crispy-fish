package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.datatype.underscore_pairs.UnderscorePairReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleStringStagingLineReaderTest {

    private static final String STAGING_LINE_NORMAL = "mock UnderscorePairReader<String> doesn't care";

    private SimpleStringStagingLineReader simpleStringStagingLineReader;

    @Mock
    UnderscorePairReader<String> underscorePairReader;

    @Before
    public void setup() {
        simpleStringStagingLineReader = new SimpleStringStagingLineReader(underscorePairReader);
    }

    @Test
    public void whenGetRunNumberItShouldGetRun() throws Exception {
        String run = "1";
        when(simpleStringStagingLineReader.getRunNumber(STAGING_LINE_NORMAL)).thenReturn(run);

        String actual = simpleStringStagingLineReader.getRunNumber(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "run");
        assertThat(actual).isSameAs(run);
    }

    @Test
    public void whenGetRegisteredClassItShouldGetClass() throws Exception {
        String clazz = "cs";
        when(simpleStringStagingLineReader.getRegisteredClass(STAGING_LINE_NORMAL)).thenReturn(clazz);

        String actual = simpleStringStagingLineReader.getRegisteredClass(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "class");
        assertThat(actual).isSameAs(clazz);
    }

    @Test
    public void whenGetRegisteredNumberItShouldGetNumber() throws Exception {
        String number = "12";
        when(simpleStringStagingLineReader.getRegisteredNumber(STAGING_LINE_NORMAL)).thenReturn(number);

        String actual = simpleStringStagingLineReader.getRegisteredNumber(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "number");
        assertThat(actual).isSameAs(number);
    }

    @Test
    public void whenGetRunRawTimeItShouldGetTm() throws Exception {
        String tm = "45.678";
        when(simpleStringStagingLineReader.getRunRawTime(STAGING_LINE_NORMAL)).thenReturn(tm);

        String actual = simpleStringStagingLineReader.getRunRawTime(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "tm");
        assertThat(actual).isSameAs(tm);
    }

    @Test
    public void whenGetRunPenaltyItShouldGetPenalty() throws Exception {
        String penalty = "1";
        when(simpleStringStagingLineReader.getRunPenalty(STAGING_LINE_NORMAL)).thenReturn(penalty);

        String actual = simpleStringStagingLineReader.getRunPenalty(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "penalty");
        assertThat(actual).isSameAs(penalty);
    }

    @Test
    public void whenGetRegisteredDriverNameItShouldGetDriver() throws Exception {
        String driver = "Anon Imoose";
        when(simpleStringStagingLineReader.getRegisteredDriverName(STAGING_LINE_NORMAL)).thenReturn(driver);

        String actual = simpleStringStagingLineReader.getRegisteredDriverName(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "driver");
        assertThat(actual).isSameAs(driver);
    }

    @Test
    public void whenGetRegisteredDriverCarItShouldGetCar() throws Exception {
        String car = "1990 Mzada Tiama";
        when(simpleStringStagingLineReader.getRegisteredDriverCar(STAGING_LINE_NORMAL)).thenReturn(car);

        String actual = simpleStringStagingLineReader.getRegisteredDriverCar(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "car");
        assertThat(actual).isSameAs(car);
    }

    @Test
    public void whenGetRegisteredDriverCarColorItShouldGetCc() throws Exception {
        String cc = "red";
        when(simpleStringStagingLineReader.getRegisteredDriverCarColor(STAGING_LINE_NORMAL)).thenReturn(cc);

        String actual = simpleStringStagingLineReader.getRegisteredDriverCarColor(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "cc");
        assertThat(actual).isSameAs(cc);
    }

    @Test
    public void whenGetDriverPositionInClassForDayItShouldGetPos() throws Exception {
        String pos = "2/5";
        when(simpleStringStagingLineReader.getDriverPositionInClassForDay(STAGING_LINE_NORMAL)).thenReturn(pos);

        String actual = simpleStringStagingLineReader.getDriverPositionInClassForDay(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "pos");
        assertThat(actual).isSameAs(pos);
    }

    @Test
    public void whenGetDriverBestTimeOfDayItShouldGetBestTime() throws Exception {
        String bestTime = "44.567";
        when(simpleStringStagingLineReader.getDriverBestTimeOfDay(STAGING_LINE_NORMAL)).thenReturn(bestTime);

        String actual = simpleStringStagingLineReader.getDriverBestTimeOfDay(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "besttime");
        assertThat(actual).isSameAs(bestTime);
    }

    @Test
    public void whenGetRunPaxTimeItShouldGetPaxed() throws Exception {
        String paxed = "34.567";
        when(simpleStringStagingLineReader.getRunPaxTime(STAGING_LINE_NORMAL)).thenReturn(paxed);

        String actual = simpleStringStagingLineReader.getRunPaxTime(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "paxed");
        assertThat(actual).isSameAs(paxed);
    }

    @Test
    public void whenGetRunTimestampItShouldGetTod() throws Exception {
        String tod = "1425830220 - 11:57:00";
        when(simpleStringStagingLineReader.getRunTimestamp(STAGING_LINE_NORMAL)).thenReturn(tod);

        String actual = simpleStringStagingLineReader.getRunTimestamp(STAGING_LINE_NORMAL);

        verify(underscorePairReader).get(STAGING_LINE_NORMAL, "tod");
        assertThat(actual).isSameAs(tod);
    }
}
