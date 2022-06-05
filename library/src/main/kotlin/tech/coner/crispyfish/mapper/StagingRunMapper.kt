package tech.coner.crispyfish.mapper

import tech.coner.crispyfish.filetype.staging.StagingFileAssistant
import tech.coner.crispyfish.filetype.staging.StagingLineReader
import tech.coner.crispyfish.model.*

internal class StagingRunMapper(
    private val assistant: StagingFileAssistant,
    private val reader: StagingLineReader<String>,
    registrations: List<Registration>,
) {

    private val registrationsBySignage by lazy { registrations.associateBy { it.signage } }

    fun toStagingRun(stagingLine: String): StagingRun {
        val stagingLineRegistration = toStagingLineRegistration(stagingLine)
        return StagingRun(
            stagingRegistration = stagingLineRegistration,
            registration = stagingLineRegistration?.signage?.let { registrationsBySignage[it] },
            run = toRun(stagingLine)
        )
    }

    private fun toStagingLineRegistration(stagingLine: String): StagingRegistration? {
        val driverClass = reader.getRegisteredDriverClass(stagingLine)?.uppercase()
        val driverNumber = reader.getRegisteredDriverNumber(stagingLine)
        return if (driverClass != null || driverNumber != null) {
            StagingRegistration(
                classing = driverClass,
                number = driverNumber,
                name = reader.getRegisteredDriverName(stagingLine),
                car = reader.getRegisteredDriverCar(stagingLine),
                carColor = reader.getRegisteredDriverCarColor(stagingLine)
            )
        } else {
            null
        }
    }

    private fun toRun(stagingFileLine: String): Run {
        val raw = reader.getRunRawTime(stagingFileLine)
        val pax = reader.getRunPaxTime(stagingFileLine)
        val penalty = reader.getRunPenalty(stagingFileLine)
        val runPenaltyType = assistant.convertStagingRunPenaltyStringToPenaltyType(penalty).getOrElse { PenaltyType.UNKNOWN }
        return Run(
            number = reader.getRunNumber(stagingFileLine)?.toIntOrNull(),
            rawTime = assistant.convertStagingTimeStringToDuration(raw).getOrNull(),
            paxTime = assistant.convertStagingTimeStringToDuration(pax).getOrNull(),
            penaltyType = runPenaltyType,
            cones = when {
                runPenaltyType === PenaltyType.CONE -> {
                    penalty
                        ?.let { assistant.convertStagingRunPenaltyStringToConeCount(it).getOrNull() }
                }
                else -> null
            },
            timeScored = null,
            timeScratchAsString = raw,
            timeScratchAsDuration = assistant.convertStagingTimeStringToDuration(raw).getOrNull()
        )
    }
}