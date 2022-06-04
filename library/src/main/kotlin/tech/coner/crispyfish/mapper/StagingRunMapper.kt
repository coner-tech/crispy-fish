package tech.coner.crispyfish.mapper

import tech.coner.crispyfish.filetype.staging.StagingFile
import tech.coner.crispyfish.model.Registration
import tech.coner.crispyfish.model.StagingRun

internal class StagingRunMapper(
    private val stagingFile: StagingFile,
    private val registrations: List<Registration>,
) {

    private val reader by lazy { stagingFile.modelReader() }
    private val registrationsBySignage by lazy { registrations.associateBy { it.signage } }

    fun toStagingRun(stagingLine: String): StagingRun {
        val stagingLineRegistration = reader.readStagingLineRegistration(stagingLine)
        return StagingRun(
            stagingRegistration = stagingLineRegistration,
            registration = stagingLineRegistration?.signage?.let { registrationsBySignage[it] },
            run = reader.readRun(stagingLine)
        )
    }
}