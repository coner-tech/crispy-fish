package tech.coner.crispyfish.mapper

import tech.coner.crispyfish.filetype.staginglog.StagingLogLineReader
import tech.coner.crispyfish.model.StagingLogRow

internal class StagingLogRowMapper(
    private val stagingRunMapper: StagingRunMapper,
    private val reader: StagingLogLineReader
) {

    fun toStagingLogRow(line: String): StagingLogRow? {
        val timestamp = reader.getTimestamp(line) ?: return null
        val sequenceRow = reader.getSequenceRow(line)?.toIntOrNull() ?: return null
        return StagingLogRow(
            timestamp = timestamp,
            sequenceRow = sequenceRow,
            stagingRun = stagingRunMapper.toStagingRun(line)
        )
    }
}
