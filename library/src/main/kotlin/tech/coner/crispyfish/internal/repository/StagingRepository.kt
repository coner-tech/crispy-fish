package tech.coner.crispyfish.internal.repository

import tech.coner.crispyfish.internal.filetype.staging.StagingFileLocator
import tech.coner.crispyfish.mapper.StagingRunMapper
import tech.coner.crispyfish.model.AllStagingRuns
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.RegistrationsBySignage
import kotlin.io.path.readLines

internal class StagingRepository(
    private val stagingFileLocator: StagingFileLocator,
    private val stagingRunMapper: StagingRunMapper
) {

    fun getAllStagingRuns(
        eventDay: EventDay,
        registrationsBySignage: RegistrationsBySignage,
    ): AllStagingRuns {
        return stagingFileLocator(eventDay).file.readLines()
            .map { line -> stagingRunMapper.toStagingRun(line, registrationsBySignage) }
            .let(::AllStagingRuns)
    }
}