package tech.coner.crispyfish.internal.filetype.staginglog

import tech.coner.crispyfish.filetype.EventControlFile
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.util.orCaseInsensitiveMatch
import kotlin.io.path.nameWithoutExtension

internal class StagingLogFileLocator(
    private val eventControlFile: EventControlFile
) {

    operator fun invoke(eventDay: EventDay): StagingLogFile {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.${eventDay.stagingLogFileExtension}")
            .orCaseInsensitiveMatch()
            .let { StagingLogFile(it) }
    }

    private val EventDay.stagingLogFileExtension: String
        get() = when (this) {
            EventDay.ONE -> "st1_log"
            EventDay.TWO -> "st2_log"
        }
}