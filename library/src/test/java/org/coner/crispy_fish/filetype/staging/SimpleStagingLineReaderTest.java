package org.coner.crispy_fish.filetype.staging;

import org.coner.crispy_fish.datatype.underscore_pairs.SimpleUnderscorePairReader;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleStagingLineReaderTest {

    private SimpleStagingLineReader simpleStagingLineReader;

    @Mock
    SimpleUnderscorePairReader underscorePairReader;

    @Before
    public void setup() {
        simpleStagingLineReader = new SimpleStagingLineReader(underscorePairReader);
    }

    @Test
    public void whenSetStagingLineItShouldPassToUnderscorePairReader() {
        String stagingLine = "key_value1_key2_value2";

        simpleStagingLineReader.setStagingLine(stagingLine);

        verify(underscorePairReader).setPairs(stagingLine);
    }

    @Test
    public void whenGetRunItShouldGetRun() throws Exception {
        String run = "1";
        when(simpleStagingLineReader.getRun()).thenReturn(run);

        String actual = simpleStagingLineReader.getRun();

        verify(underscorePairReader).get("run");
        assertThat(actual).isSameAs(run);
    }

    @Test
    public void whenGetClazzItShouldGetClass() throws Exception {
        String clazz = "cs";
        when(simpleStagingLineReader.getClazz()).thenReturn(clazz);

        String actual = simpleStagingLineReader.getClazz();

        verify(underscorePairReader).get("class");
        assertThat(actual).isSameAs(clazz);
    }

    @Test
    public void whenGetNumberItShouldGetNumber() throws Exception {
        String number = "12";
        when(simpleStagingLineReader.getNumber()).thenReturn(number);

        String actual = simpleStagingLineReader.getNumber();

        verify(underscorePairReader).get("number");
        assertThat(actual).isSameAs(number);
    }

    @Test
    public void whenGetTmItShouldGetTm() throws Exception {
        String tm = "45.678";
        when(simpleStagingLineReader.getTm()).thenReturn(tm);

        String actual = simpleStagingLineReader.getTm();

        verify(underscorePairReader).get("tm");
        assertThat(actual).isSameAs(tm);
    }

    @Test
    public void whenGetPenaltyItShouldGetPenalty() throws Exception {
        String penalty = "2";
        when(simpleStagingLineReader.getPenalty()).thenReturn(penalty);

        String actual = simpleStagingLineReader.getPenalty();

        verify(underscorePairReader).get("penalty");
        assertThat(actual).isSameAs(penalty);
    }

    @Test
    public void whenGetDriverItShouldGetDriver() throws Exception {
        String driver = "John Q Test";
        when(simpleStagingLineReader.getDriver()).thenReturn(driver);

        String actual = simpleStagingLineReader.getDriver();

        verify(underscorePairReader).get("driver");
        assertThat(actual).isSameAs(driver);
    }

    @Test
    public void whenGetCarItShouldGetCar() throws Exception {
        String car = "1990 Mzada Tiama";
        when(simpleStagingLineReader.getCar()).thenReturn(car);

        String actual = simpleStagingLineReader.getCar();

        verify(underscorePairReader).get("car");
        assertThat(actual).isSameAs(car);
    }

    @Test
    public void whenGetCcItShouldGetCc() throws Exception {
        String cc = "red";
        when(simpleStagingLineReader.getCc()).thenReturn(cc);

        String actual = simpleStagingLineReader.getCc();

        verify(underscorePairReader).get("cc");
        assertThat(actual).isSameAs(cc);
    }

    @Test
    public void whenGetPosItShouldGetPos() throws Exception {
        String pos = "2/5";
        when(simpleStagingLineReader.getPos()).thenReturn(pos);

        String actual = simpleStagingLineReader.getPos();

        verify(underscorePairReader).get("pos");
        assertThat(actual).isSameAs(pos);
    }

    @Test
    public void whenGetBestTimeItShouldGetBestTime() throws Exception {
        String bestTime = "44.567";
        when(simpleStagingLineReader.getBestTime()).thenReturn(bestTime);

        String actual = simpleStagingLineReader.getBestTime();

        verify(underscorePairReader).get("besttime");
        assertThat(actual).isSameAs(bestTime);
    }

    @Test
    public void whenGetPaxedItShouldGetPaxed() throws Exception {
        String paxed = "34.567";
        when(simpleStagingLineReader.getPaxed()).thenReturn(paxed);

        String actual = simpleStagingLineReader.getPaxed();

        verify(underscorePairReader).get("paxed");
        assertThat(actual).isSameAs(paxed);
    }

    @Test
    public void whenGetTodItShouldGetTod() throws Exception {
        String tod = "1425830220 - 11:57:00";
        when(simpleStagingLineReader.getTod()).thenReturn(tod);

        String actual = simpleStagingLineReader.getTod();

        verify(underscorePairReader).get("tod");
        assertThat(actual).isSameAs(tod);
    }
}
