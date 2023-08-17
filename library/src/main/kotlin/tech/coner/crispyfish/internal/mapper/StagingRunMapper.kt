package tech.coner.crispyfish.internal.mapper

import tech.coner.crispyfish.internal.filetype.staging.StagingFileAssistant
import tech.coner.crispyfish.internal.filetype.staging.StagingLineReader
import tech.coner.crispyfish.model.*

internal class StagingRunMapper(
    private val reader: StagingLineReader<String>,
    private val classingMapper: ClassingMapper,
) {

    fun toStagingRun(stagingLine: String, registrationsBySignage: RegistrationsBySignage): StagingRun {
        val stagingLineRegistration = toStagingLineRegistration(stagingLine)
        return StagingRun(
            stagingRegistration = stagingLineRegistration,
            registration = stagingLineRegistration?.signage?.let { registrationsBySignage.value[it] },
            run = toRun(stagingLine)
        )
    }

    private fun toStagingLineRegistration(stagingLine: String): StagingRegistration? {
        val driverClass = reader.getRegisteredDriverClass(stagingLine)?.uppercase()
        val driverNumber = reader.getRegisteredDriverNumber(stagingLine)
        return if (driverClass != null || driverNumber != null) {
            StagingRegistration(
                signage = Signage(
                    classing = classingMapper.toClassing(driverClass),
                    number = driverNumber
                ),
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
        val runPenaltyType = StagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty).getOrElse { PenaltyType.UNKNOWN }
        return Run(
            number = reader.getRunNumber(stagingFileLine)?.toIntOrNull(),
            rawTime = StagingFileAssistant.convertStagingTimeStringToDuration(raw).getOrNull(),
            paxTime = StagingFileAssistant.convertStagingTimeStringToDuration(pax).getOrNull(),
            penaltyType = runPenaltyType,
            cones = when {
                runPenaltyType === PenaltyType.CONE -> {
                    penalty
                        ?.let { StagingFileAssistant.convertStagingRunPenaltyStringToConeCount(it).getOrNull() }
                }
                else -> null
            },
            timeScored = null,
            timeScratchAsString = raw,
            timeScratchAsDuration = StagingFileAssistant.convertStagingTimeStringToDuration(raw).getOrNull()
        )
    }
}