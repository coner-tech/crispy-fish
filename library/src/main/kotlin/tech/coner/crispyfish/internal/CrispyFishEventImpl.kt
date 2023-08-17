package tech.coner.crispyfish.internal

import tech.coner.crispyfish.CrispyFishEvent
import tech.coner.crispyfish.internal.filetype.eventcontrolfile.EventControlFile
import tech.coner.crispyfish.internal.datatype.underscorepairs.SimpleStringUnderscorePairReader
import tech.coner.crispyfish.internal.filetype.registration.RegistrationFileLocator
import tech.coner.crispyfish.internal.filetype.registration.RegistrationLineColumnReader
import tech.coner.crispyfish.internal.filetype.staging.SimpleStringStagingLineReader
import tech.coner.crispyfish.internal.filetype.staging.StagingFileLocator
import tech.coner.crispyfish.internal.filetype.staginglog.StagingLogFileLocator
import tech.coner.crispyfish.internal.filetype.staginglog.StagingLogLineReader
import tech.coner.crispyfish.internal.repository.RegistrationRepository
import tech.coner.crispyfish.internal.repository.StagingLogRepository
import tech.coner.crispyfish.internal.repository.StagingRepository
import tech.coner.crispyfish.internal.mapper.ClassingMapper
import tech.coner.crispyfish.internal.mapper.RegistrationMapper
import tech.coner.crispyfish.internal.mapper.StagingLogRowMapper
import tech.coner.crispyfish.internal.mapper.StagingRunMapper
import tech.coner.crispyfish.model.*

internal class CrispyFishEventImpl(
    private val eventControlFile: EventControlFile
) : CrispyFishEvent {


    private fun buildRegistrationRepository(allClassDefinitions: AllClassDefinitions): RegistrationRepository {
        val registrationFile = RegistrationFileLocator(eventControlFile).invoke()
        val registrationLineColumnReader = RegistrationLineColumnReader(registrationFile)
        return RegistrationRepository(
            registrationReader = registrationLineColumnReader,
            registrationMapper = RegistrationMapper(
                classingMapper = ClassingMapper(allClassDefinitions),
                reader = registrationLineColumnReader
            ),
        )
    }

    override fun queryAllRegistrations(
        allClassDefinitions: AllClassDefinitions
    ): AllRegistrations {
        return buildRegistrationRepository(allClassDefinitions)
            .getAllRegistrations()
    }


    override fun queryAllRegistrationsBySignage(
        allClassDefinitions: AllClassDefinitions,
        allRegistrations: AllRegistrations
    ): RegistrationsBySignage {
        return buildRegistrationRepository(allClassDefinitions)
            .getRegistrationsBySignage(allRegistrations)
    }

    override fun queryAllStagingRuns(
        eventDay: EventDay,
        allClassDefinitions: AllClassDefinitions,
        registrationsBySignage: RegistrationsBySignage
    ): AllStagingRuns {
        return StagingRepository(
            stagingFileLocator = StagingFileLocator(eventControlFile),
            stagingRunMapper = StagingRunMapper(
                reader = SimpleStringStagingLineReader(
                    underscorePairReader = SimpleStringUnderscorePairReader()
                ),
                classingMapper = ClassingMapper(allClassDefinitions),
            )
        )
            .getAllStagingRuns(eventDay, registrationsBySignage)
    }

    override fun queryAllStagingLogRows(
        eventDay: EventDay,
        allClassDefinitions: AllClassDefinitions,
        registrationsBySignage: RegistrationsBySignage
    ): AllStagingLogRows {
        val underscorePaidReader = SimpleStringUnderscorePairReader()
        return StagingLogRepository(
            stagingLogFileLocator = StagingLogFileLocator(eventControlFile),
            stagingLogRowMapper = StagingLogRowMapper(
                stagingRunMapper = StagingRunMapper(
                    reader = SimpleStringStagingLineReader(underscorePaidReader),
                    classingMapper = ClassingMapper(allClassDefinitions)
                ),
                reader = StagingLogLineReader(underscorePaidReader)
            )
        )
            .getAllStagingLogRows(eventDay, registrationsBySignage)
    }
}