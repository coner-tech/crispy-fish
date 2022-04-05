package tech.coner.crispyfish.filetype.staging

import assertk.all
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isSameAs
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import tech.coner.crispyfish.model.*
import java.time.Duration
import kotlin.Result as KotlinResult

class StagingLineModelReaderTest {

    private lateinit var stagingLineModelReader: StagingLineModelReader<String>

    @MockK lateinit var stagingFileAssistant: StagingFileAssistant
    @MockK lateinit var stagingLineReader: StagingLineReader<String>

    private val STAGING_LINE_MOCK = "mock staging line (content not representative)"

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { stagingLineReader.getRegisteredDriverName(any()) } returns "driverName"
        every { stagingLineReader.getRegisteredDriverCar(any()) } returns "driverCar"
        every { stagingLineReader.getRegisteredDriverCarColor(any()) } returns "driverCarColor"
        every { stagingLineReader.getRegisteredDriverClass(any()) } returns "driverClassing"
        every { stagingLineReader.getRegisteredDriverNumber(any()) } returns "driverNumber"
        every { stagingLineReader.getRunNumber(any()) } returns "1"
        every { stagingLineReader.getRunRawTime(any()) } returns "raw"
        every { stagingLineReader.getRunPaxTime(any() )} returns "pax"
        every { stagingLineReader.getRunPenalty(any()) } returns "penalty"
        every { stagingFileAssistant.convertStagingTimeStringToDuration(any()) } returns KotlinResult.success(Duration.ZERO)
        every { stagingFileAssistant.convertStagingTimeStringToDuration(any()) } returns KotlinResult.success(Duration.ZERO)
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(any()) } returns KotlinResult.success(PenaltyType.CLEAN)
        stagingLineModelReader = StagingLineModelReader(stagingFileAssistant, stagingLineReader)
    }

    @Test
    fun whenReadDriver() {
        val driverName = "driver name"
        val driverCar = "driver car"
        val driverClassing = "DRIVER CLASSING"
        val driverNumber = "driver number"
        every { stagingLineReader.getRegisteredDriverName(STAGING_LINE_MOCK) } returns driverName 
        every { stagingLineReader.getRegisteredDriverCar(STAGING_LINE_MOCK) } returns driverCar
        every { stagingLineReader.getRegisteredDriverClass(STAGING_LINE_MOCK) } returns driverClassing
        every { stagingLineReader.getRegisteredDriverNumber(STAGING_LINE_MOCK) } returns driverNumber

        val actual = stagingLineModelReader.readRegistration(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull().all {
            name().isSameAs(driverName)
            car().isSameAs(driverCar)
            classing().isSameAs(driverClassing)
            number().isSameAs(driverNumber)
        }
    }

    @Test
    fun whenReadRunNormalClean() {
        val raw = "raw"
        val pax = "pax"
        val penalty = "penalty"
        val rawTime = Duration.ofSeconds(56).plusMillis(789)
        val paxTime = Duration.ofSeconds(45).plusMillis(678)
        val penaltyType = PenaltyType.CLEAN
        every { stagingLineReader.getRunRawTime(STAGING_LINE_MOCK) } returns raw
        every { stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK) } returns pax
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingTimeStringToDuration(raw) } returns KotlinResult.success(rawTime)
        every { stagingFileAssistant.convertStagingTimeStringToDuration(pax) } returns KotlinResult.success(paxTime)
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.success(penaltyType)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull().all {
            rawTime().isSameAs(rawTime)
            paxTime().isSameAs(paxTime)
            penaltyType().isSameAs(penaltyType)
            cones().isNull()
        }
    }

    @Test
    fun whenReadRunNormalCone() {
        val penalty = "2"
        val cones = 2
        val penaltyType = PenaltyType.CONE
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.success(penaltyType)
        every { stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty) } returns KotlinResult.success(cones)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull().all {
            penaltyType().isSameAs(penaltyType)
            cones().isEqualTo(cones)
        }
    }

    @Test
    fun whenReadRunNormalOtherPenaltyUnknownCones() {
        val penalty = "DNF"
        val penaltyType = PenaltyType.DID_NOT_FINISH
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.success(penaltyType)

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).isNotNull().all {
            penaltyType().isSameAs(penaltyType)
            cones().isEqualTo(null)
        }
    }

    @Test
    fun whenReadRunConvertRawTimeFailsItShouldReturnRunWithoutRawTime() {
        val raw = "raw"
        every { stagingLineReader.getRunRawTime(STAGING_LINE_MOCK) } returns raw
        every { stagingFileAssistant.convertStagingTimeStringToDuration(raw) } returns KotlinResult.failure(StagingLineException(""))

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).rawTime().isNull()
    }

    @Test
    fun whenReadRunConvertPaxTimeThrowsOnCleanRunItShouldReturnRunWithoutPaxTime() {
        val pax = "pax"
        val penalty = "penalty"
        every { stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK) } returns pax
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.success(PenaltyType.CLEAN)
        every { stagingFileAssistant.convertStagingTimeStringToDuration(pax) } returns KotlinResult.failure(StagingLineException(""))

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).paxTime().isNull()
    }

    @Test
    fun whenReadRunConvertPaxTimeThrowsOnDnfRunItShouldReturnRunWithout() {
        val pax = "pax"
        val penalty = "penalty"
        every { stagingLineReader.getRunPaxTime(STAGING_LINE_MOCK) } returns pax
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.success(PenaltyType.DID_NOT_FINISH)
        every { stagingFileAssistant.convertStagingTimeStringToDuration(pax) } returns KotlinResult.failure(StagingLineException(""))

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).all {
            penaltyType().isSameAs(PenaltyType.DID_NOT_FINISH)
            paxTime().isNull()
        }
    }

    @Test
    fun whenReadRunConvertPenaltyToPenaltyTypeThrowsItShouldReturnRunWithUnknownPenalty() {
        val penalty = "eleventy"
        every { stagingLineReader.getRunPenalty(STAGING_LINE_MOCK) } returns penalty
        every { stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty) } returns KotlinResult.failure(StagingLineException(""))

        val actual = stagingLineModelReader.readRun(STAGING_LINE_MOCK)

        assertThat(actual).penaltyType().isSameAs(PenaltyType.UNKNOWN)
    }
}