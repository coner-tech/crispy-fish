package org.coner.crispy_fish.filetype.st1;

import org.coner.crispy_fish.datatype.underscore_pairs.UnderscorePairReader;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class St1LineReaderTest {

    private St1LineReader st1LineReader;

    @Mock
    UnderscorePairReader underscorePairReader;

    @Before
    public void setup() {
        st1LineReader = new St1LineReader(underscorePairReader);
    }

    @Test
    public void whenSetSt1LineItShouldPassToUnderscorePairReader() {
        String st1Line = "key_value1_key2_value2";

        st1LineReader.setSt1Line(st1Line);

        verify(underscorePairReader).setPairs(st1Line);
    }

    @Test
    public void whenGetRunItShouldGetRun() throws Exception {
        String run = "1";
        when(st1LineReader.getRun()).thenReturn(run);

        String actual = st1LineReader.getRun();

        verify(underscorePairReader).get("run");
        assertThat(actual).isSameAs(run);
    }

    @Test
    public void whenGetClazzItShouldGetClass() throws Exception {
        String clazz = "cs";
        when(st1LineReader.getClazz()).thenReturn(clazz);

        String actual = st1LineReader.getClazz();

        verify(underscorePairReader).get("class");
        assertThat(actual).isSameAs(clazz);
    }

    @Test
    public void whenGetNumberItShouldGetNumber() throws Exception {
        String number = "12";
        when(st1LineReader.getNumber()).thenReturn(number);

        String actual = st1LineReader.getNumber();

        verify(underscorePairReader).get("number");
        assertThat(actual).isSameAs(number);
    }

    @Test
    public void whenGetTmItShouldGetTm() throws Exception {
        String tm = "45.678";
        when(st1LineReader.getTm()).thenReturn(tm);

        String actual = st1LineReader.getTm();

        verify(underscorePairReader).get("tm");
        assertThat(actual).isSameAs(tm);
    }

    @Test
    public void whenGetPenaltyItShouldGetPenalty() throws Exception {
        String penalty = "2";
        when(st1LineReader.getPenalty()).thenReturn(penalty);

        String actual = st1LineReader.getPenalty();

        verify(underscorePairReader).get("penalty");
        assertThat(actual).isSameAs(penalty);
    }

    @Test
    public void whenGetDriverItShouldGetDriver() throws Exception {
        String driver = "John Q Test";
        when(st1LineReader.getDriver()).thenReturn(driver);

        String actual = st1LineReader.getDriver();

        verify(underscorePairReader).get("driver");
        assertThat(actual).isSameAs(driver);
    }

    @Test
    public void whenGetCarItShouldGetCar() throws Exception {
        String car = "1990 Mzada Tiama";
        when(st1LineReader.getCar()).thenReturn(car);

        String actual = st1LineReader.getCar();

        verify(underscorePairReader).get("car");
        assertThat(actual).isSameAs(car);
    }

    @Test
    public void whenGetCcItShouldGetCc() throws Exception {
        String cc = "red";
        when(st1LineReader.getCc()).thenReturn(cc);

        String actual = st1LineReader.getCc();

        verify(underscorePairReader).get("cc");
        assertThat(actual).isSameAs(cc);
    }

    @Test
    public void whenGetPosItShouldGetPos() throws Exception {
        String pos = "2/5";
        when(st1LineReader.getPos()).thenReturn(pos);

        String actual = st1LineReader.getPos();

        verify(underscorePairReader).get("pos");
        assertThat(actual).isSameAs(pos);
    }

    @Test
    public void whenGetBestTimeItShouldGetBestTime() throws Exception {
        String bestTime = "44.567";
        when(st1LineReader.getBestTime()).thenReturn(bestTime);

        String actual = st1LineReader.getBestTime();

        verify(underscorePairReader).get("besttime");
        assertThat(actual).isSameAs(bestTime);
    }

    @Test
    public void whenGetPaxedItShouldGetPaxed() throws Exception {
        String paxed = "34.567";
        when(st1LineReader.getPaxed()).thenReturn(paxed);

        String actual = st1LineReader.getPaxed();

        verify(underscorePairReader).get("paxed");
        assertThat(actual).isSameAs(paxed);
    }

    @Test
    public void whenGetTodItShouldGetTod() throws Exception {
        String tod = "1425830220 - 11:57:00";
        when(st1LineReader.getTod()).thenReturn(tod);

        String actual = st1LineReader.getTod();

        verify(underscorePairReader).get("tod");
        assertThat(actual).isSameAs(tod);
    }
}
