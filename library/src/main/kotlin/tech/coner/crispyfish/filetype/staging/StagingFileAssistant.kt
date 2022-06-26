package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.PenaltyType
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.time.Duration
import java.time.format.DateTimeParseException
import java.util.regex.Pattern


class StagingFileAssistant {
    fun buildStagingFilenameFilter(eventControlFile: EventControlFile, eventDay: EventDay): StagingFilenameFilter {
        val originalFilePattern: Pattern = when (eventDay) {
            EventDay.ONE -> StagingFilenames.ORIGINAL_FILE_DAY_1
            EventDay.TWO -> StagingFilenames.ORIGINAL_FILE_DAY_2
        }
        val originalFileBaseName = getOriginalFileBaseName(eventControlFile)
        return StagingFilenameFilter(
            originalFileBaseName,
            originalFilePattern
        )
    }

    fun getOriginalFileBaseName(eventControlFile: EventControlFile): String {
        return eventControlFile.file.nameWithoutExtension
    }

    @Throws(StagingLineException::class)
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
                when (penalty.toUpperCase()) {
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
