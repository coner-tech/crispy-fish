package org.coner.crispyfish.filetype.staging

import org.coner.crispyfish.model.EventDay
import org.coner.crispyfish.model.PenaltyType
import org.coner.crispyfish.filetype.ecf.EventControlFile
import org.junit.Before
import org.junit.Test

import java.nio.file.Paths
import java.time.Duration

import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.*

class StagingFileAssistantTest {

    private lateinit var assistant: StagingFileAssistant

    @Before
    fun setup() {
        assistant = StagingFileAssistant()
    }

    @Test
    fun whenBuildStagingFilenameFilterForDayOneItShouldReturnIt() {
        val eventControlFilePath = Paths.get("foo", "bar", "baz.ecf")
        val eventControlFile = mock(EventControlFile::class.java)
        `when`(eventControlFile.path).thenReturn(eventControlFilePath)
        `when`(eventControlFile.isTwoDayEvent).thenReturn(false)

        val actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.ONE)

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter::class.java)
        assertThat(actual.eventFileOriginalStagingBaseName).isEqualTo("baz")
        assertThat(actual.originalFilePattern).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_1)
    }

    @Test
    fun whenBuildStagingFilenameFilterForDayTwoItShouldReturnIt() {
        val eventControlFilePath = Paths.get("foo", "bar", "baz.ecf")
        val eventControlFile = mock(EventControlFile::class.java)
        `when`(eventControlFile.path).thenReturn(eventControlFilePath)
        `when`(eventControlFile.isTwoDayEvent).thenReturn(true)

        val actual = assistant.buildStagingFilenameFilter(eventControlFile, EventDay.TWO)

        assertThat(actual)
                .isNotNull()
                .isInstanceOf(StagingFilenameFilter::class.java)
        assertThat(actual.eventFileOriginalStagingBaseName).isEqualTo("baz")
        assertThat(actual.originalFilePattern).isSameAs(StagingFilenames.ORIGINAL_FILE_DAY_2)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingTimeStringToDurationNormalItShouldWorkLol() {
        val expected = Duration.ofSeconds(45).plusMillis(678)

        val actual = assistant.convertStagingTimeStringToDuration("45.678")

        assertThat(actual).isEqualByComparingTo(expected)
    }

    @Test(expected = StagingLineException::class)
    @Throws(Exception::class)
    fun whenConvertStagingTimeStringToDurationInvalidItShouldThrow() {
        assistant.convertStagingTimeStringToDuration("invalid")
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeEmptyItShouldReturnClean() {
        val penalty = ""
        val expected = PenaltyType.CLEAN

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSameAs(expected)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeDidNotFinish() {
        val penalty = "dnf"
        val expected = PenaltyType.DID_NOT_FINISH

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSameAs(expected)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeDisqualified() {
        val penalty = "dsq"
        val expected = PenaltyType.DISQUALIFIED

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSameAs(expected)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeRerun() {
        val penalty = "rrn"
        val expected = PenaltyType.RERUN

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSameAs(expected)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeCone() {
        val penalty = "2"
        val expected = PenaltyType.CONE

        val actual = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)

        assertThat(actual).isSameAs(expected)
    }

    @Test(expected = StagingLineException::class)
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyStringToPenaltyTypeUnrecognized() {
        val penalty = "*unrecognized*"

        assistant.convertStagingRunPenaltyStringToPenaltyType(penalty)
    }

    @Test
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyToConeCountNormal() {
        val penalty = "8"
        val expected = 8

        val actual = assistant.convertStagingRunPenaltyStringToConeCount(penalty)

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = StagingLineException::class)
    @Throws(Exception::class)
    fun whenConvertStagingRunPenaltyToConeCountInvalid() {
        val penalty = "-1"

        assistant.convertStagingRunPenaltyStringToConeCount(penalty)
    }
}
