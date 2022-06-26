package tech.coner.crispyfish.filetype.staging

import assertk.assertThat
import assertk.assertions.*
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.PenaltyType
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.File
import java.time.Duration

class StagingFileAssistantTest {

    private lateinit var assistant: StagingFileAssistant

    @Before
    fun setup() {
        assistant = StagingFileAssistant()
    }

    @Test
    fun whenBuildStagingFilenameFilterForDayOneItShouldReturnIt() {
        val eventControlFileFile = File("/foo/bar/baz.ecf")
        val eventControlFile = mock(EventControlFile::class.java)
        `when`(eventControlFile.file).thenReturn(eventControlFileFile)
        `when`(eventControlFile.isTwoDayEvent).thenReturn(false)

        val actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.ONE)

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter::class)
        assertThat(actual.eventFileOriginalStagingBaseName).isEqualTo("baz")
        assertThat(actual.originalFilePattern).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_1)
    }

    @Test
    fun whenBuildStagingFilenameFilterForDayTwoItShouldReturnIt() {
        val eventControlFileFile = File("foo/bar/baz.ecf")
        val eventControlFile = mock(EventControlFile::class.java)
        `when`(eventControlFile.file).thenReturn(eventControlFileFile)
        `when`(eventControlFile.isTwoDayEvent).thenReturn(true)

        val actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.TWO)

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter::class.java)
        assertThat(actual.eventFileOriginalStagingBaseName).isEqualTo("baz")
        assertThat(actual.originalFilePattern).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_2)
    }

    @Test
    fun whenConvertStagingTimeStringToDurationNormalItShouldWorkLol() {
        val actual = assistant.convertStagingTimeStringToDuration("45.678")

        assertThat(actual).isSuccess().isEqualTo(Duration.ofSeconds(45).plusMillis(678))
    }

    @Test
    fun whenConvertStagingTimeStringToDurationInvalidItShouldThrow() {
        val actual = assistant.convertStagingTimeStringToDuration("invalid")

        assertThat(actual).isFailure().isInstanceOf(StagingLineException::class)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeEmptyItShouldReturnClean() {
        val penalty = ""

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isEqualTo(PenaltyType.CLEAN)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeDidNotFinish() {
        val penalty = "dnf"

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isEqualTo(PenaltyType.DID_NOT_FINISH)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeDisqualified() {
        val penalty = "dsq"

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isEqualTo(PenaltyType.DISQUALIFIED)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeRerun() {
        val penalty = "rrn"

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isEqualTo(PenaltyType.RERUN)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeCone() {
        val penalty = "2"

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isEqualTo(PenaltyType.CONE)
    }

    @Test
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeUnrecognized() {
        val penalty = "*unrecognized*"

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSuccess().isSameAs(PenaltyType.UNKNOWN)
    }

    @Test
    fun whenConvertStagingRunPenaltyToConeCountNormal() {
        val penalty = "8"

        val actual = assistant.convertStagingRunPenaltyStringToConeCount(penalty)

        assertThat(actual).isSuccess().isEqualTo(8)
    }

    @Test
    fun whenConvertStagingRunPenaltyToConeCountInvalid() {
        val penalty = "-1"

        val actual = assistant.convertStagingRunPenaltyStringToConeCount(penalty)

        assertThat(actual).isFailure()
            .isInstanceOf(StagingLineException::class.java)
    }
}
