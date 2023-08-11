package tech.coner.crispyfish.filetype.staging

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import java.nio.file.Path
import kotlin.io.path.nameWithoutExtension

class StagingFileLocator(
        private val eventControlFile: EventControlFile,
) {
    fun locate(eventDay: EventDay): Path {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.${eventDay.stagingFileExtension}")
    }

    private val EventDay.stagingFileExtension: String
        get() = when (this) {
            EventDay.ONE -> "st1"
            EventDay.TWO -> "st2"
        }
}
