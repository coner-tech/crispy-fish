package tech.coner.crispyfish.filetype.registration

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File
import java.io.FileFilter
import java.util.regex.Pattern

class RegistrationFileLocator(val eventControlFile: EventControlFile) {

    fun locate(): RegistrationFile {
        val folder = eventControlFile.file.parentFile
        val file = folder.resolve("${eventControlFile.file.nameWithoutExtension}.rgg")
        return RegistrationFile(file)
    }
}