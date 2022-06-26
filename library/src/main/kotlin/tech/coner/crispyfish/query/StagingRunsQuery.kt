package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.staging.StagingFile
import tech.coner.crispyfish.mapper.StagingRunMapper
import tech.coner.crispyfish.model.StagingRun

internal class StagingRunsQuery(
    private val stagingFile: StagingFile,
    private val stagingRunMapper: StagingRunMapper
) {

    fun query(): List<StagingRun> {
        return stagingFile.file.readLines()
            .map { line -> stagingRunMapper.toStagingRun(line) }
    }
}