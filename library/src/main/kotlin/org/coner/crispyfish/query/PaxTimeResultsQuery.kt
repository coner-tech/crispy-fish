package org.coner.crispyfish.query

import org.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import org.coner.crispyfish.model.*
import org.coner.crispyfish.filetype.ecf.EventControlFile

import java.time.Duration
import java.util.HashMap

class PaxTimeResultsQuery(
        private val classDefinitionFile: ClassDefinitionFile,
        private val eventControlFile: EventControlFile,
        private val eventDay: EventDay = EventDay.ONE
) {

    @Throws(QueryException::class)
    fun query(): List<Result> {
        val driverBestPaxResults = HashMap<String, Result>()
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
            run.timeScratchAsString = stagingFile.reader.getRunPaxTime(stagingFileLine)
            run.timeScratchAsDuration = run.paxTime
            var penaltyDuration = Duration.ZERO
            when (run.penaltyType) {
                PenaltyType.CONE -> {
                    // no-op
                    // pax time from staging file already has penalty time added
                }
                PenaltyType.DID_NOT_FINISH -> penaltyDuration = Duration.ofDays(1)
                PenaltyType.DISQUALIFIED -> penaltyDuration = Duration.ofDays(2)
                PenaltyType.RERUN -> throw IllegalStateException("Run should be omitted from results")
                PenaltyType.CLEAN-> { /* no-op */ }
                else -> throw IllegalStateException("Unrecognized penalty type: " + run.penaltyType!!)
            }// cone penalties already included in pax time
            if (run.paxTime == null && penaltyDuration !== Duration.ZERO) {
                run.paxTime = Duration.ZERO
            }
            run.timeScored = run.paxTime!!.plus(penaltyDuration)
            val shouldPutResult: Boolean
            shouldPutResult = if (driverBestPaxResults.containsKey(registration.driverIdentityKey)) {
                val bestResult = driverBestPaxResults[registration.driverIdentityKey]
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
            driverBestPaxResults[registration.driverIdentityKey] = result
        }
        val results = driverBestPaxResults.values
                .sortedBy { it.run?.timeScored }
                .toList()
        var position = 1
        for (result in results) {
            result.position = position++
        }
        return results
    }

}
