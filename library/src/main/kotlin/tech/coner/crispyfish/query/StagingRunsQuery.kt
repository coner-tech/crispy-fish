package tech.coner.crispyfish.query

import tech.coner.crispyfish.StagingRun
import tech.coner.crispyfish.filetype.staging.StagingFile
import tech.coner.crispyfish.model.Run
import tech.coner.crispyfish.model.StagingLineRegistration

class StagingRunsQuery(
    private val stagingFile: StagingFile
) {

    fun query(): List<StagingRun> {
        val reader = stagingFile.modelReader()
        return stagingFile.file.readLines()
            .map { line ->
                StagingRun(
                    stagingLineRegistration = reader.readRegistration(line),
                    run = reader.readRun(line)
                )
            }
    }
}