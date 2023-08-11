package tech.coner.crispyfish.query

import tech.coner.crispyfish.filetype.staginglog.StagingLogFile
import tech.coner.crispyfish.mapper.StagingLogRowMapper
import tech.coner.crispyfish.model.StagingLogRow
import kotlin.io.path.readLines

interface StagingLogRowsQuery {
    operator fun invoke(): List<StagingLogRow>
}

internal class StagingLogRowsQueryImpl(
    private val file: StagingLogFile,
    internal val mapper: StagingLogRowMapper
) : StagingLogRowsQuery {

    override fun invoke(): List<StagingLogRow> {
        return file.file.readLines()
            .mapNotNull { line -> mapper.toStagingLogRow(line) }
    }
}