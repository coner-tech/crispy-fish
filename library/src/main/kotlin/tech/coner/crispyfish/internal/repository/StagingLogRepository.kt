package tech.coner.crispyfish.internal.repository

import tech.coner.crispyfish.internal.filetype.staginglog.StagingLogFileLocator
import tech.coner.crispyfish.internal.mapper.StagingLogRowMapper
import tech.coner.crispyfish.model.AllStagingLogRows
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.RegistrationsBySignage
import kotlin.io.path.forEachLine

internal class StagingLogRepository(
    private val stagingLogFileLocator: StagingLogFileLocator,
    private val stagingLogRowMapper: StagingLogRowMapper
) {

    fun getAllStagingLogRows(
        eventDay: EventDay,
        registrationsBySignage: RegistrationsBySignage
    ): AllStagingLogRows {
        return buildList {
            stagingLogFileLocator(eventDay).file.forEachLine { line ->
                    stagingLogRowMapper.toStagingLogRow(line, registrationsBySignage)
                        ?.also { add(it) }
            }
        }
            .let(::AllStagingLogRows)

    }
}