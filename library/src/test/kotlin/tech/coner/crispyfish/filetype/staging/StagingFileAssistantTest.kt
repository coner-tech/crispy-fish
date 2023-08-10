package tech.coner.crispyfish.filetype.staging

import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.model.PenaltyType
import java.time.Duration

class StagingFileAssistantTest {

    private lateinit var assistant: StagingFileAssistant

    @BeforeEach
    fun setup() {
        assistant = StagingFileAssistant()
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
