package tech.coner.crispyfish.internal.filetype.registration

import tech.coner.crispyfish.internal.filetype.eventcontrolfile.EventControlFile
import tech.coner.crispyfish.internal.util.orCaseInsensitiveMatch
import kotlin.io.path.nameWithoutExtension

@JvmInline
internal value class RegistrationFileLocator(
    private val eventControlFile: EventControlFile
) {

    operator fun invoke(): RegistrationFile {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.rgg")
            .orCaseInsensitiveMatch()
            .let { RegistrationFile(it) }
    }
}