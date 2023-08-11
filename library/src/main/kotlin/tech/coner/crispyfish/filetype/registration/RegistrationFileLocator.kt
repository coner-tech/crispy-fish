package tech.coner.crispyfish.filetype.registration

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import kotlin.io.path.nameWithoutExtension

class RegistrationFileLocator(val eventControlFile: EventControlFile) {

    fun locate(): RegistrationFile {
        return RegistrationFile(
            eventControlFile.file.resolveSibling("${eventControlFile.file.nameWithoutExtension}.rgg")
        )
    }
}