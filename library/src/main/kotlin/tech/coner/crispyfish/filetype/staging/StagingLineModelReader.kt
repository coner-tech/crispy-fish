package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.mapper.ClassingMapper
import tech.coner.crispyfish.model.PenaltyType
import tech.coner.crispyfish.model.Run
import tech.coner.crispyfish.model.Signage
import tech.coner.crispyfish.model.StagingRegistration

class StagingLineModelReader<L>(
    private val classingMapper: ClassingMapper,
    private val stagingFileAssistant: StagingFileAssistant,
    private val stagingLineReader: StagingLineReader<L>
) {

    fun readStagingLineRegistration(stagingLine: L): StagingRegistration {
        val driverClass = stagingLineReader.getRegisteredDriverClass(stagingLine)?.uppercase()
        val driverNumber = stagingLineReader.getRegisteredDriverNumber(stagingLine)
        return StagingRegistration(
            signage = if (driverClass != null && driverNumber != null) {
                Signage(
                    classing = classingMapper.toClassing(driverClass),
                    number = driverNumber
                )
            } else {
                null
            },
            name = stagingLineReader.getRegisteredDriverName(stagingLine),
            car = stagingLineReader.getRegisteredDriverCar(stagingLine),
            carColor = stagingLineReader.getRegisteredDriverCarColor(stagingLine)
        )
    }

    fun readRun(stagingFileLine: L): Run {
        val raw = stagingLineReader.getRunRawTime(stagingFileLine)
        val pax = stagingLineReader.getRunPaxTime(stagingFileLine)
        val penalty = stagingLineReader.getRunPenalty(stagingFileLine)
        val runPenaltyType = stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty).getOrElse { PenaltyType.UNKNOWN }
        return Run(
            number = stagingLineReader.getRunNumber(stagingFileLine)?.toIntOrNull(),
            rawTime = stagingFileAssistant.convertStagingTimeStringToDuration(raw).getOrNull(),
            paxTime = stagingFileAssistant.convertStagingTimeStringToDuration(pax).getOrNull(),
            penaltyType = runPenaltyType,
            cones = when {
                runPenaltyType === PenaltyType.CONE -> {
                    penalty
                        ?.let { stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(it).getOrNull() }
                }
                else -> null
            },
            timeScored = null,
            timeScratchAsString = raw,
            timeScratchAsDuration = stagingFileAssistant.convertStagingTimeStringToDuration(raw).getOrNull()
        )
    }
}
