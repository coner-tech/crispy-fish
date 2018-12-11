package org.coner.crispy_fish.query

import org.coner.crispy_fish.domain.*
import org.coner.crispy_fish.filetype.ecf.EventControlFile
import org.crispy_fish.filetype.staging.StagingLineDomainReader
import org.crispy_fish.filetype.staging.StagingLineReader

import java.time.Duration
import java.util.HashMap
import kotlin.streams.toList

class PaxTimeResultsQuery(private val eventControlFile: EventControlFile, private val stagingLineReader: StagingLineReader<String>, private val stagingLineDomainReader: StagingLineDomainReader<String>) {

    @Throws(QueryException::class)
    fun query(stagingFileLines: List<String>): List<Result> {
        val driverBestPaxResults = HashMap<Numbers, Result>()
        for (stagingFileLine in stagingFileLines) {
            val driver = stagingLineDomainReader.readDriver(stagingFileLine)
            val run = stagingLineDomainReader.readRun(stagingFileLine)
            if (driver == null || run == null) {
                continue
            }
            if (run.penaltyType?.shouldOmitRunFromResults == true) continue
            run.timeScratchAsString = stagingLineReader.getRunPaxTime(stagingFileLine)
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
            shouldPutResult = if (driverBestPaxResults.containsKey(driver.numbers)) {
                val bestResult = driverBestPaxResults[driver.numbers]
                run.timeScored?.compareTo(bestResult?.run?.timeScored!!) ?: 0 < 0
            } else {
                true
            }
            if (!shouldPutResult) {
                continue
            }
            val result = Result()
            result.driver = driver
            result.run = run
            driverBestPaxResults[driver.numbers!!] = result
        }
        val results = driverBestPaxResults.values.stream()
                .sorted(compareBy { it.run?.timeScored })
                .toList()
        var position = 1
        for (result in results) {
            result.position = position++
        }
        return results
    }

}
