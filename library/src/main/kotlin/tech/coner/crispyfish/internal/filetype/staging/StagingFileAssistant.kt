package tech.coner.crispyfish.internal.filetype.staging

import tech.coner.crispyfish.model.PenaltyType
import java.time.Duration
import java.time.format.DateTimeParseException


internal object StagingFileAssistant {

    fun convertStagingTimeStringToDuration(stagingTime: String?): Result<Duration> {
        return try {
            Result.success(Duration.parse("PT${stagingTime}S"))
        } catch (e: DateTimeParseException) {
            Result.failure(
                StagingLineException("Staging time $stagingTime cannot be parsed to Duration", e)
            )
        }

    }

    fun convertStagingRunPenaltyStringToPenaltyType(penalty: String?): Result<PenaltyType> {
        return Result.success(
            if (penalty?.isNotEmpty() == true) {
                when (penalty.uppercase()) {
                    "DNF" -> PenaltyType.DID_NOT_FINISH
                    "DSQ" -> PenaltyType.DISQUALIFIED
                    "RRN" -> PenaltyType.RERUN
                    else -> convertStagingRunPenaltyStringToConeCount(penalty)
                        .map { PenaltyType.CONE }
                        .getOrElse { PenaltyType.UNKNOWN }
                }
            } else {
                PenaltyType.CLEAN
            }
        )
    }

    fun convertStagingRunPenaltyStringToConeCount(penalty: String): Result<Int> {
        return try {
            Result.success(Integer.parseUnsignedInt(penalty))
        } catch (e: NumberFormatException) {
            Result.failure(
                StagingLineException(
                    "Unable to convert staging penalty cones $penalty to cone count",
                    e
                )
            )
        }
    }
}
