package tech.coner.crispyfish.filetype

import tech.coner.crispyfish.internal.filetype.ecf.EventControlFileAssistant
import tech.coner.crispyfish.internal.filetype.ecf.NotEventControlFileException
import java.nio.file.Path

@JvmInline
value class EventControlFile(
    val file: Path,
) {

    init {
        if (!EventControlFileAssistant.isEventControlFile(file)) {
            throw NotEventControlFileException(file)
        }
    }
}
