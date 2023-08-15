package tech.coner.crispyfish.internal.filetype.registration

import tech.coner.crispyfish.filetype.EventControlFile
import tech.coner.crispyfish.util.orCaseInsensitiveMatch
import kotlin.io.path.nameWithoutExtension

@JvmInline
value class RegistrationFileLocator(
    private val eventControlFile: EventControlFile
) {

    operator fun invoke(): RegistrationFile {
        return eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.rgg")
            .orCaseInsensitiveMatch()
            .let { RegistrationFile(it) }
    }
}