package org.coner.crispyfish.filetype.ecf

import org.coner.crispyfish.datatype.underscorepairs.SimpleStringUnderscorePairReader
import org.coner.crispyfish.filetype.registration.RegistrationFileLocator
import org.coner.crispyfish.filetype.staging.SimpleStringStagingLineReader
import org.coner.crispyfish.filetype.staging.StagingFile
import org.coner.crispyfish.filetype.staging.StagingFileAssistant
import org.coner.crispyfish.filetype.staging.StagingFileLocator
import org.coner.crispyfish.model.EventDay
import java.io.File
import java.io.FileNotFoundException

class EventControlFile(
        val file: File,
        val isTwoDayEvent: Boolean,
        val conePenalty: Int,
        private val ecfAssistant: EventControlFileAssistant = EventControlFileAssistant(),
        private val stagingFileAssistant: StagingFileAssistant = StagingFileAssistant()
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
            file = stagingFileLocator.locate(eventDay) ?: throw FileNotFoundException(),
            reader = SimpleStringStagingLineReader(SimpleStringUnderscorePairReader()),
            assistant = stagingFileAssistant

    )
}
