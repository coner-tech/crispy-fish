package tech.coner.crispyfish.filetype.staging

import org.assertj.core.api.Assertions.assertThat
import tech.coner.crispyfish.model.PenaltyType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.time.Duration

@RunWith(MockitoJUnitRunner::class)
class StagingLineModelReaderTest {

    private lateinit var stagingLineModelReader: StagingLineModelReader<String>

    @Mock
    lateinit var stagingFileAssistant: StagingFileAssistant
    @Mock
    lateinit var stagingLineReader: StagingLineReader<String>

    private val STAGING_LINE_MOCK = "mock staging line (content not representative)"

    @Before
    fun setup() {
        stagingLineModelReader = StagingLineModelReader(stagingFileAssistant, stagingLineReader)
    }

    @Test
    fun whenReadDriver() {
        val driverName = "driver name"
        val driverCar = "driver car"
        val driverClassing = "DRIVER CLASSING"
        val driverNumber = "driver number"
        `when`<String>(stagingLineReader.getRegisteredDriverName(STAGING_LINE_MOCK)).thenReturn(driverName)
        `when`<String>(stagingLineReader.getRegisteredDriverCar(STAGING_LINE_MOCK)).thenReturn(driverCar)
        `when`<String>(stagingLineReader.getRegisteredDriverClass(STAGING_LINE_MOCK)).thenReturn(driverClassing)
        `when`<String>(stagingLineReader.getRegisteredDriverNumber(STAGING_LINE_MOCK)).thenReturn(driverNumber)

        val actual = stagingLineModelReader.readRegistration(STAGING_LINE_MOCK)

        assertThat(actual!!).isNotNull()
        assertThat(actual.name).isSameAs(driverName)
        assertThat(actual.car).isSameAs(driverCar)
        assertThat(actual.classing).isSameAs(driverClassing)
        assertThat(actual.number).isSameAs(driverNumber)
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunNormalClean() {
        val raw = "raw"
        val pax = "pax"
        val penalty = "penalty"
        val rawTime = Duration.ofSeconds(56).plusMillis(789)
        val paxTime = Duration.ofSeconds(45).plusMillis(678)
        val penaltyType = PenaltyType.CLEAN
        `when`<String>(stagingLineReader.getRunRawTime(STAGING_LINE_MOCK)).thenReturn(raw)
        `when`<String>(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax)
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingTimeStringToDuration(raw)).thenReturn(rawTime)
        `when`(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenReturn(paxTime)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull()
        assertThat(actual?.rawTime).isSameAs(rawTime)
        assertThat(actual?.paxTime).isSameAs(paxTime)
        assertThat(actual?.penaltyType).isSameAs(penaltyType)
        assertThat(actual?.cones).isEqualTo(0)
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunNormalCone() {
        val penalty = "2"
        val cones = 2
        val penaltyType = PenaltyType.CONE
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty)).thenReturn(cones)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull()
        assertThat(actual?.penaltyType).isSameAs(penaltyType)
        assertThat(actual?.cones).isEqualTo(cones)
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunNormalOtherPenaltyUnknownCones() {
        val penalty = "DNF"
        val penaltyType = PenaltyType.DID_NOT_FINISH
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(penaltyType)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull()
        assertThat(actual?.penaltyType).isSameAs(penaltyType)
        assertThat(actual?.cones).isEqualTo(null)
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunConvertRawTimeThrowsItShouldReturnNull() {
        val raw = "raw"
        `when`<String>(stagingLineReader.getRunRawTime(STAGING_LINE_MOCK)).thenReturn(raw)
        `when`(stagingFileAssistant.convertStagingTimeStringToDuration(raw)).thenThrow(StagingLineException::class.java)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNull()
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunConvertPaxTimeThrowsOnCleanRunItShouldReturnNull() {
        val pax = "pax"
        val penalty = "penalty"
        `when`<String>(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax)
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(PenaltyType.CLEAN)
        `when`(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenThrow(StagingLineException::class.java)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNull()
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunConvertPaxTimeThrowsOnDnfRunItShouldReturnRun() {
        val pax = "pax"
        val penalty = "penalty"
        `when`<String>(stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK)).thenReturn(pax)
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenReturn(PenaltyType.DID_NOT_FINISH)
        `when`(stagingFileAssistant.convertStagingTimeStringToDuration(pax)).thenThrow(StagingLineException::class.java)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull()
    }

    @Test
    @Throws(Exception::class)
    fun whenReadRunConvertPenaltyToPenaltyTypeThrowsItShouldReturnNull() {
        val penalty = "eleventy"
        `when`<String>(stagingLineReader.getRunPenalty(STAGING_LINE_MOCK)).thenReturn(penalty)
        `when`(stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)).thenThrow(StagingLineException::class.java)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNull()
    }
}