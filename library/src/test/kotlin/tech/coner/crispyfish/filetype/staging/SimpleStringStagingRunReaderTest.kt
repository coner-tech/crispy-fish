package tech.coner.crispyfish.filetype.staging

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.datatype.underscorepairs.SimpleStringUnderscorePairReader

class SimpleStringStagingRunReaderTest {

    private lateinit var simpleStringStagingLineReader: SimpleStringStagingLineReader

    @BeforeEach
    fun setup() {
        simpleStringStagingLineReader = SimpleStringStagingLineReader(SimpleStringUnderscorePairReader())
    }

    @Test
    fun whenGetRunNumberItShouldGetRun() {
        val actual = simpleStringStagingLineReader.getRunNumber(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("1")
    }

    @Test
    fun whenGetRegisteredClassItShouldGetClass() {
        val actual = simpleStringStagingLineReader.getRegisteredDriverClass(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("STR")
    }

    @Test
    fun whenGetRegisteredNumberItShouldGetNumber() {
        val actual = simpleStringStagingLineReader.getRegisteredDriverNumber(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("23")
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRunRawTimeItShouldGetTm() {
        val actual = simpleStringStagingLineReader.getRunRawTime(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("50.115")
    }

    @Test
    fun whenGetRunPenaltyItShouldGetPenalty() {
        val actual = simpleStringStagingLineReader.getRunPenalty(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("2")
    }

    @Test
    fun whenGetRegisteredDriverNameItShouldGetDriver() {
        val actual = simpleStringStagingLineReader.getRegisteredDriverName(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("Jimmy Mckenzie")
    }

    @Test
    fun whenGetRegisteredDriverCarItShouldGetCar() {
        val actual = simpleStringStagingLineReader.getRegisteredDriverCar(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("1994 Mazda Miata")
    }

    @Test
    fun whenGetRegisteredDriverCarColorItShouldGetCc() {
        val actual = simpleStringStagingLineReader.getRegisteredDriverCarColor(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("White")
    }

    @Test
    fun whenGetDriverPositionInClassForDayItShouldGetPos() {
        val actual = simpleStringStagingLineReader.getDriverPositionInClassForDay(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("2/2")
    }

    @Test
    fun whenGetDriverBestTimeOfDayItShouldGetBestTime() {
        val actual = simpleStringStagingLineReader.getDriverBestTimeOfDay(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("54.115")
    }

    @Test
    fun whenGetRunPaxTimeItShouldGetPaxed() {
        val actual = simpleStringStagingLineReader.getRunPaxTime(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("44.753")
    }

    @Test
    fun whenGetRunTimestampItShouldGetTod() {
        val actual = simpleStringStagingLineReader.getRunTimestamp(STAGING_LINE_NORMAL)

        assertThat(actual).isEqualTo("1594078509 - 19:35:09")
    }

    companion object {

        private const val STAGING_LINE_NORMAL = "_run_1_class_STR_number_23_tm_50.115_penalty_2_driver_Jimmy Mckenzie_car_1994 Mazda Miata_cc_White_pos_2/2_besttime_54.115_diff_+6.571_diff1_+6.571_paxed_44.753_tod_1594078509 - 19:35:09_pxp_2_rwp_2"
    }
}
