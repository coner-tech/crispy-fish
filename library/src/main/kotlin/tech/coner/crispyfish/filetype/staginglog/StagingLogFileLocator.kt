package tech.coner.crispyfish.filetype.staginglog

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import java.nio.file.Path
import kotlin.io.path.nameWithoutExtension

class StagingLogFileLocator(
    private val eventControlFile: EventControlFile
) {

    operator fun invoke(eventDay: EventDay): Path {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.st${eventDay.sequence}_log")
    }
}