package org.coner.crispyfish.filetype.ecf

import org.coner.crispyfish.filetype.registration.RegistrationFileLocator
import org.coner.crispyfish.filetype.staging.StagingFileAssistant
import org.coner.crispyfish.filetype.staging.StagingFileLocator
import java.nio.file.Path

class EventControlFile(
        val path: Path,
        val isTwoDayEvent: Boolean,
        val conePenalty: Int,
        private val ecfAssistant: EventControlFileAssistant = EventControlFileAssistant()
) {

    val file = path.toFile()

    init {
        if (!ecfAssistant.isEventControlFilePath(path)) {
            throw NotEventControlFilePathException(path)
        }
    }

    val registrationFileLocator by lazy { RegistrationFileLocator(this) }
    val stagingFileLocator by lazy { StagingFileLocator(this) }
}
