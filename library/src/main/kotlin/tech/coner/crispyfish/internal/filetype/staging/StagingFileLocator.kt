package tech.coner.crispyfish.internal.filetype.staging

import tech.coner.crispyfish.filetype.EventControlFile
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.util.orCaseInsensitiveMatch
import kotlin.io.path.nameWithoutExtension

internal class StagingFileLocator(
    private val eventControlFile: EventControlFile,
) {
    operator fun invoke(eventDay: EventDay): StagingFile {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.${eventDay.stagingFileExtension}")
            .orCaseInsensitiveMatch()
            .let { StagingFile(it) }
    }

    private val EventDay.stagingFileExtension: String
        get() = when (this) {
            EventDay.ONE -> "st1"
            EventDay.TWO -> "st2"
        }
}
