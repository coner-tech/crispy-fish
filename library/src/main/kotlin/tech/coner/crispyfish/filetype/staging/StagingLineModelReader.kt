package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.model.PenaltyType
import tech.coner.crispyfish.model.Run
import tech.coner.crispyfish.model.StagingLineRegistration
import java.time.Duration

class StagingLineModelReader<L>(
        private val stagingFileAssistant: StagingFileAssistant,
        private val stagingLineReader: StagingLineReader<L>
) {

    fun readRegistration(stagingLine: L): StagingLineRegistration? {
        val registration = StagingLineRegistration(
                number = stagingLineReader.getRegisteredDriverNumber(stagingLine),
                classing = stagingLineReader.getRegisteredDriverClass(stagingLine)?.toUpperCase(),
                name = stagingLineReader.getRegisteredDriverName(stagingLine),
                car = stagingLineReader.getRegisteredDriverCar(stagingLine),
                carColor = stagingLineReader.getRegisteredDriverCarColor(stagingLine)
        )
        return when {
            registration.number.isNullOrBlank() -> null
            registration.classing.isNullOrBlank() -> null
            else -> registration
        }
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
