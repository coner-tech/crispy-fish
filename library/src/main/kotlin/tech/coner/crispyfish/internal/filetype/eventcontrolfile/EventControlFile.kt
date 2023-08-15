package tech.coner.crispyfish.internal.filetype.eventcontrolfile

import java.nio.file.Path

@JvmInline
internal value class EventControlFile(
    val file: Path,
) {

    init {
        if (!EventControlFileAssistant.isEventControlFile(file)) {
            throw NotEventControlFileException(file)
        }
    }
}
