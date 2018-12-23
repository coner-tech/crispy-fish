package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.model.*
import org.coner.crispyfish.filetype.ecf.EventControlFile

import java.time.Duration
import java.util.HashMap
import kotlin.streams.toList

class RawTimeResultsQuery(
        private val classDefinitionFile: ClassDefinitionFile,
        private val eventControlFile: EventControlFile,
        private val eventDay: EventDay = EventDay.ONE
) {

    @Throws(QueryException::class)
    fun query(): List<Result> {
        val driverBestRawResults = HashMap<String, Result>()
        val stagingFile = eventControlFile.stagingFile(eventDay)
        val stagingLineModelReader = stagingFile.modelReader()
        val stagingFileLines = stagingFile.file.readLines()
        val categories = CategoriesQuery(classDefinitionFile).query()
        val handicaps = HandicapsQuery(classDefinitionFile).query()
        val registrations = RegistrationsQuery(
                eventControlFile = eventControlFile,
                categories = categories,
                handicaps = handicaps
        ).query()
        val registrationMapper = eventControlFile.registrationFile().mapper()
        for (stagingFileLine in stagingFileLines) {
            val registration = stagingLineModelReader.readRegistration(stagingFileLine)
            val run = stagingLineModelReader.readRun(stagingFileLine)
            if (registration == null || run == null) {
                continue
            }
            if (run.penaltyType?.shouldOmitRunFromResults == true) continue
            run.timeScratchAsString = stagingFile.reader.getRunRawTime(stagingFileLine)
            run.timeScratchAsDuration = run.rawTime
            var penaltyDuration = Duration.ZERO
            when (run.penaltyType) {
                PenaltyType.CONE -> penaltyDuration = Duration.ofSeconds((run.cones!! * eventControlFile.conePenalty).toLong())
                PenaltyType.DID_NOT_FINISH -> penaltyDuration = Duration.ofDays(1)
                PenaltyType.DISQUALIFIED -> penaltyDuration = Duration.ofDays(2)
                PenaltyType.RERUN -> { throw IllegalStateException("Run should be omitted from results") }
                PenaltyType.CLEAN -> { /* no-op */ }
            }
            run.timeScored = run.rawTime!!.plus(penaltyDuration)
            val shouldPutResult: Boolean
            shouldPutResult = if (driverBestRawResults.containsKey(registration.driverIdentityKey)) {
                val bestResult = driverBestRawResults[registration.driverIdentityKey]
                run.timeScored?.compareTo(bestResult?.run?.timeScored!!) ?: 0 < 0
            } else {
                true
            }
            if (!shouldPutResult) {
                continue
            }
            val result = Result()
            result.driver = registrationMapper.toRegistration(
                    stagingLineRegistration = registration,
                    categories = categories,
                    handicaps = handicaps,
                    registrations = registrations
            )
            result.run = run
            driverBestRawResults[registration.driverIdentityKey] = result
        }
        val results = driverBestRawResults.values
                .sortedBy { it.run?.timeScored }
                .toList()
        var position = 1
        for (result in results) {
            result.position = position++
        }
        return results
    }

}
