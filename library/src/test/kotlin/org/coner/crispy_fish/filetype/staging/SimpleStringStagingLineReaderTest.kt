package org.coner.crispy_fish.filetype.staging

import org.coner.crispy_fish.datatype.underscore_pairs.UnderscorePairReader
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class SimpleStringStagingLineReaderTest {

    private lateinit var simpleStringStagingLineReader: SimpleStringStagingLineReader

    @Mock
    lateinit var underscorePairReader: UnderscorePairReader<String>

    @Before
    fun setup() {
        simpleStringStagingLineReader = SimpleStringStagingLineReader(underscorePairReader)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunNumberItShouldGetRun() {
        val run = "1"
        `when`<String>(simpleStringStagingLineReader.getRunNumber(STAGING_LINE_NORMAL)).thenReturn(run)

        val actual = simpleStringStagingLineReader.getRunNumber(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "run")
        assertThat(actual).isSameAs(run)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRegisteredClassItShouldGetClass() {
        val clazz = "cs"
        `when`<String>(simpleStringStagingLineReader.getRegisteredDriverClass(STAGING_LINE_NORMAL)).thenReturn(clazz)

        val actual = simpleStringStagingLineReader.getRegisteredDriverClass(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "class")
        assertThat(actual).isSameAs(clazz)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRegisteredNumberItShouldGetNumber() {
        val number = "12"
        `when`<String>(simpleStringStagingLineReader.getRegisteredDriverNumber(STAGING_LINE_NORMAL)).thenReturn(number)

        val actual = simpleStringStagingLineReader.getRegisteredDriverNumber(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "number")
        assertThat(actual).isSameAs(number)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunRawTimeItShouldGetTm() {
        val tm = "45.678"
        `when`<String>(simpleStringStagingLineReader.getRunRawTime(STAGING_LINE_NORMAL)).thenReturn(tm)

        val actual = simpleStringStagingLineReader.getRunRawTime(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "tm")
        assertThat(actual).isSameAs(tm)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunPenaltyItShouldGetPenalty() {
        val penalty = "1"
        `when`<String>(simpleStringStagingLineReader.getRunPenalty(STAGING_LINE_NORMAL)).thenReturn(penalty)

        val actual = simpleStringStagingLineReader.getRunPenalty(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "penalty")
        assertThat(actual).isSameAs(penalty)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRegisteredDriverNameItShouldGetDriver() {
        val driver = "Anon Imoose"
        `when`<String>(simpleStringStagingLineReader.getRegisteredDriverName(STAGING_LINE_NORMAL)).thenReturn(driver)

        val actual = simpleStringStagingLineReader.getRegisteredDriverName(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "driver")
        assertThat(actual).isSameAs(driver)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRegisteredDriverCarItShouldGetCar() {
        val car = "1990 Mzada Tiama"
        `when`<String>(simpleStringStagingLineReader.getRegisteredDriverCar(STAGING_LINE_NORMAL)).thenReturn(car)

        val actual = simpleStringStagingLineReader.getRegisteredDriverCar(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "car")
        assertThat(actual).isSameAs(car)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRegisteredDriverCarColorItShouldGetCc() {
        val cc = "red"
        `when`<String>(simpleStringStagingLineReader.getRegisteredDriverCarColor(STAGING_LINE_NORMAL)).thenReturn(cc)

        val actual = simpleStringStagingLineReader.getRegisteredDriverCarColor(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "cc")
        assertThat(actual).isSameAs(cc)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetDriverPositionInClassForDayItShouldGetPos() {
        val pos = "2/5"
        `when`<String>(simpleStringStagingLineReader.getDriverPositionInClassForDay(STAGING_LINE_NORMAL)).thenReturn(pos)

        val actual = simpleStringStagingLineReader.getDriverPositionInClassForDay(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "pos")
        assertThat(actual).isSameAs(pos)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetDriverBestTimeOfDayItShouldGetBestTime() {
        val bestTime = "44.567"
        `when`<String>(simpleStringStagingLineReader.getDriverBestTimeOfDay(STAGING_LINE_NORMAL)).thenReturn(bestTime)

        val actual = simpleStringStagingLineReader.getDriverBestTimeOfDay(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "besttime")
        assertThat(actual).isSameAs(bestTime)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunPaxTimeItShouldGetPaxed() {
        val paxed = "34.567"
        `when`<String>(simpleStringStagingLineReader.getRunPaxTime(STAGING_LINE_NORMAL)).thenReturn(paxed)

        val actual = simpleStringStagingLineReader.getRunPaxTime(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "paxed")
        assertThat(actual).isSameAs(paxed)
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunTimestampItShouldGetTod() {
        val tod = "1425830220 - 11:57:00"
        `when`<String>(simpleStringStagingLineReader.getRunTimestamp(STAGING_LINE_NORMAL)).thenReturn(tod)

        val actual = simpleStringStagingLineReader.getRunTimestamp(STAGING_LINE_NORMAL)

        verify<UnderscorePairReader<String>>(underscorePairReader).get(STAGING_LINE_NORMAL, "tod")
        assertThat(actual).isSameAs(tod)
    }

    companion object {

        private const val STAGING_LINE_NORMAL = "mock UnderscorePairReader<String> doesn't care"
    }
}
