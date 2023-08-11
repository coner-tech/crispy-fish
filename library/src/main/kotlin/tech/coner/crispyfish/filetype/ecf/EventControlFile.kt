package tech.coner.crispyfish.filetype.ecf

import tech.coner.crispyfish.datatype.underscorepairs.SimpleStringUnderscorePairReader
import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.filetype.registration.RegistrationFileLocator
import tech.coner.crispyfish.filetype.registration.RegistrationLineColumnReader
import tech.coner.crispyfish.filetype.staging.SimpleStringStagingLineReader
import tech.coner.crispyfish.filetype.staging.StagingFile
import tech.coner.crispyfish.filetype.staging.StagingFileAssistant
import tech.coner.crispyfish.filetype.staging.StagingFileLocator
import tech.coner.crispyfish.filetype.staginglog.StagingLogFile
import tech.coner.crispyfish.filetype.staginglog.StagingLogFileLocator
import tech.coner.crispyfish.filetype.staginglog.StagingLogLineReader
import tech.coner.crispyfish.mapper.ClassingMapper
import tech.coner.crispyfish.mapper.RegistrationMapper
import tech.coner.crispyfish.mapper.StagingLogRowMapper
import tech.coner.crispyfish.mapper.StagingRunMapper
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.query.*
import java.nio.file.Path

class EventControlFile(
    val file: Path,
    val classDefinitionFile: ClassDefinitionFile,
    val isTwoDayEvent: Boolean,
    private val stagingFileAssistant: StagingFileAssistant = StagingFileAssistant(),
    ecfAssistant: EventControlFileAssistant = EventControlFileAssistant()
) {

    init {
        if (!ecfAssistant.isEventControlFile(file)) {
            throw NotEventControlFileException(file)
        }
    }

    val registrationFileLocator by lazy { RegistrationFileLocator(this) }

    fun registrationFile() = registrationFileLocator.locate()

    val stagingFileLocator by lazy { StagingFileLocator(this) }

    fun stagingFile(eventDay: EventDay = EventDay.ONE) = StagingFile(
            file = stagingFileLocator.locate(eventDay),
    )

    private val stagingLogFileLocator by lazy { StagingLogFileLocator(this) }

    fun stagingLogFile(eventDay: EventDay = EventDay.ONE) = StagingLogFile(
        file = stagingLogFileLocator(eventDay)
    )

    fun queryCategories() = CategoriesQuery(classDefinitionFile).query()

    fun queryHandicaps() = HandicapsQuery(classDefinitionFile).query()

    fun queryRegistrations(
        categories: List<ClassDefinition> = queryCategories(),
        handicaps: List<ClassDefinition> = queryHandicaps()
    ): List<Registration> {
        val reader = RegistrationLineColumnReader(registrationFile())
        return RegistrationsQuery(
            reader = reader,
            mapper = RegistrationMapper(
                classingMapper = ClassingMapper(
                    categories = categories,
                    handicaps = handicaps
                ),
                reader = reader
            ),
            categories = categories,
            handicaps = handicaps
        )
            .query()
    }

    private fun stagingRunMapperFactory(
        categories: List<ClassDefinition> = queryCategories(),
        handicaps: List<ClassDefinition> = queryHandicaps(),
        registrations: List<Registration> = queryRegistrations()
    ) = StagingRunMapper(
        assistant = stagingFileAssistant,
        reader = SimpleStringStagingLineReader(
            underscorePairReader = SimpleStringUnderscorePairReader()
        ),
        classingMapper = ClassingMapper(
            categories = categories,
            handicaps = handicaps
        ),
        registrations = registrations,
    )

    fun queryStagingRuns(
        eventDay: EventDay = EventDay.ONE,
        categories: List<ClassDefinition> = queryCategories(),
        handicaps: List<ClassDefinition> = queryHandicaps(),
        registrations: List<Registration> = queryRegistrations()
    ): List<StagingRun> {
        val stagingFile = stagingFile(eventDay = eventDay)
        return StagingRunsQuery(
            stagingFile = stagingFile,
            stagingRunMapper = stagingRunMapperFactory(
                categories = categories,
                handicaps = handicaps,
                registrations = registrations
            )
        )
            .query()
    }

    fun queryStagingLogRows(
        eventDay: EventDay = EventDay.ONE,
        categories: List<ClassDefinition> = queryCategories(),
        handicaps: List<ClassDefinition> = queryHandicaps(),
        registrations: List<Registration> = queryRegistrations()
    ) : List<StagingLogRow> {
        return StagingLogRowsQueryImpl(
            file = stagingLogFile(eventDay),
            mapper = StagingLogRowMapper(
                stagingRunMapper = stagingRunMapperFactory(
                    categories = categories,
                    handicaps = handicaps,
                    registrations = registrations
                ),
                reader = StagingLogLineReader(
                    underscorePairReader = SimpleStringUnderscorePairReader()
                )
            )
        )
            .invoke()
    }

}
