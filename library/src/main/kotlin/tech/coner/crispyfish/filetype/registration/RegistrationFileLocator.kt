package tech.coner.crispyfish.filetype.registration

import tech.coner.crispyfish.filetype.ecf.EventControlFile
import java.io.File
import java.io.FileFilter
import java.util.regex.Pattern

class RegistrationFileLocator(val eventControlFile: EventControlFile) {

    fun locate(): RegistrationFile {
        val folder = eventControlFile.file.parentFile
        val expected = "${eventControlFile.file.nameWithoutExtension}.rgg"
        val listResult = folder.listFiles { _: File, name: String -> name.equals(expected, ignoreCase = true) }
            ?: throw RegistrationFileException("Failed to list files in parent directory of event control file")
        val file = listResult.singleOrNull()
            ?: throw RegistrationFileException("Located ${listResult.size} files matching expected registration name")
        return RegistrationFile(file)
    }
}