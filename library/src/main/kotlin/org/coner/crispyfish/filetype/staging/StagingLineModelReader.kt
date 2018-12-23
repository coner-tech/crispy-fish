package org.coner.crispyfish.filetype.staging

import org.coner.crispyfish.model.PenaltyType
import org.coner.crispyfish.model.Run

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

    fun readRun(stagingFileLine: L): Run? {
        val run = Run()
        val raw = stagingLineReader.getRunRawTime(stagingFileLine)
        val pax = stagingLineReader.getRunPaxTime(stagingFileLine)
        val penalty = stagingLineReader.getRunPenalty(stagingFileLine)
        try {
            run.rawTime = stagingFileAssistant.convertStagingTimeStringToDuration(raw)
            run.penaltyType = stagingFileAssistant.convertStagingRunPenaltyStringToPenaltyType(penalty)
            try {
                run.paxTime = stagingFileAssistant.convertStagingTimeStringToDuration(pax)
            } catch (e: StagingLineException) {
                when (run.penaltyType) {
                    PenaltyType.CONE -> throw StagingLineException("Unable to parse pax time from coned run", e)
                    PenaltyType.DID_NOT_FINISH,
                    PenaltyType.DISQUALIFIED,
                    PenaltyType.RERUN -> { /* no-op */ }
                    PenaltyType.CLEAN -> throw StagingLineException("Unable to parse pax time from clean run", e)
                    else -> throw IllegalStateException("Unrecognized run.penaltyType: " + run.penaltyType!!, e)
                }
            }

            run.cones = when {
                run.penaltyType === PenaltyType.CONE -> stagingFileAssistant.convertStagingRunPenaltyStringToConeCount(penalty!!)
                run.penaltyType === PenaltyType.CLEAN -> 0
                else -> null
            }
        } catch (e: StagingLineException) {
            return null
        }

        return run
    }
}
