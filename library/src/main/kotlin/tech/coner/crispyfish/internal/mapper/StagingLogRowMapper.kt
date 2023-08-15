package tech.coner.crispyfish.internal.mapper

import tech.coner.crispyfish.internal.filetype.staginglog.StagingLogLineReader
import tech.coner.crispyfish.model.RegistrationsBySignage
import tech.coner.crispyfish.model.StagingLogRow

internal class StagingLogRowMapper(
    private val stagingRunMapper: StagingRunMapper,
    private val reader: StagingLogLineReader
) {

    fun toStagingLogRow(line: String, registrationsBySignage: RegistrationsBySignage): StagingLogRow? {
        val timestamp = reader.getTimestamp(line) ?: return null
        val sequenceRow = reader.getSequenceRow(line)?.toIntOrNull() ?: return null
        return StagingLogRow(
            timestamp = timestamp,
            sequenceRow = sequenceRow,
            stagingRun = stagingRunMapper.toStagingRun(line, registrationsBySignage)
        )
    }
}
